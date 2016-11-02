package com.example.administrator.jsondemo2.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.administrator.jsondemo2.app.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/10/22.
 */

public class ImageLoader {
    private static final String TAG="ImageLoader";
    private static LruCache<String,Bitmap> imageCache;
    private String path;

    public ImageLoader() {
        imageCache = new LruCache<String, Bitmap>(5 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        path = Environment.getDataDirectory() + "/data/" + MyApplication.getApp().getPackageName() + "/imageCache/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
        public void setImageUrl(ImageView ivImage,String imgsrc){
            Bitmap bitmap=null;
            bitmap=getBitmapFromMemory(imgsrc);
            if (bitmap!=null){
                ivImage.setImageBitmap(bitmap);
                return;
            }else {
                bitmap=getBitmapFromDisk(imgsrc);
                if (bitmap!=null){
                    ivImage.setImageBitmap(bitmap);
                    saveBitmapToMemory(imgsrc,bitmap);
                    return;
                }
            }
            LoadImageTask task=new LoadImageTask(ivImage);
            task.execute(imgsrc);
    }
    private void saveBitmapToMemory(String imgsrc, Bitmap bitmap) {
        String key = getKeyFromImageSrc(imgsrc);
        imageCache.put(key, bitmap);
        Log.d(TAG, "saveBitmapToMemory: ");
    }

    private String getKeyFromImageSrc(String imgsrc) {

        return imgsrc.substring(imgsrc.lastIndexOf("/") + 1);
    }
    private Bitmap getBitmapFromMemory(String imgsrc) {
        Log.d(TAG, "getBitmapFromMemory: ");
        String key = getKeyFromImageSrc(imgsrc);
        return imageCache.get(key);
    }
    private Bitmap getBitmapFromDisk(String imgsrc) {
        Log.d(TAG, "getBitmapFromDisk: ");
        String fileName = getKeyFromImageSrc(imgsrc);
        return BitmapFactory.decodeFile(path + fileName);
    }
    private void saveBitmapToDisk(Bitmap bitmap, String imgSrc) {
        String fileName = getKeyFromImageSrc(imgSrc);
        File file = new File(path, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        Log.d(TAG, "saveBitmapToDisk: ");
    }


    private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView mImageView;
        String imgSrc;

        public LoadImageTask(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d(TAG, "onPostExecute: ");
            if (bitmap != null) {
                //               设置图片
                mImageView.setImageBitmap(bitmap);
                //保存到文件中
                //缓存到内存中
                saveBitmapToDisk(bitmap, imgSrc);
            }

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.d(TAG, "doInBackground: ");
            imgSrc = params[0];
            Bitmap bitmap = null;

            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //// TODO: 2016/10/22 尺寸处理
                    bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }
}
