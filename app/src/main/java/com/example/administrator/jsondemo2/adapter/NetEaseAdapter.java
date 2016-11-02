package com.example.administrator.jsondemo2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.jsondemo2.NetEase;
import com.example.administrator.jsondemo2.R;
import com.example.administrator.jsondemo2.base.MyBaseAdapter;
import com.example.administrator.jsondemo2.utils.ImageLoader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/22.
 */

public class NetEaseAdapter extends MyBaseAdapter<NetEase>{
    ImageLoader loader;
    public NetEaseAdapter(){
        loader=new ImageLoader();
    }
    @Override
    protected BaseHolder getHolder(Context context) {
        View view = View.inflate(context, R.layout.layout_item_netease, null);
        return new NetEaseHolder(view);
    }

    public class NetEaseHolder extends BaseHolder {
        @BindView(R.id.iv_image)
        ImageView mIvImage;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_date)
        TextView mTvDate;

        public NetEaseHolder(View rootView) {
            super(rootView);
        }

        @Override
        public void setData(NetEase item) {
            loader.setImageUrl(mIvImage,item.imgsrc);
            mTvTitle.setText(item.title);
            mTvDate.setText(item.lmodify);
        }
    }

//    private void setImageUrl(ImageView ivImage, String imgsrc) {
//        //开线程，访问网络，得到流，解析出图片，设置
//
//        LoadImageTask task = new LoadImageTask(ivImage);
//        task.execute(imgsrc);
//    }
//
//    public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
//
//        ImageView mImageView;
//
//        public LoadImageTask(ImageView imageView) {
//            mImageView = imageView;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            if (bitmap != null) {
//                //               设置图片
//                mImageView.setImageBitmap(bitmap);
//            } else {
//                //设置一个失败的图片，或者默认图片
//            }
//
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... params) {
//
//            Bitmap bitmap = null;
//
//            try {
//                URL url = new URL(params[0]);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    //// TODO: 2016/10/22 尺寸处理
//                    bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return bitmap;
//        }
//    }
}
