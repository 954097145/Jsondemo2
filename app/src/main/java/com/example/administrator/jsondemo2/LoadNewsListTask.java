package com.example.administrator.jsondemo2;

/**
 * Created by Administrator on 2016/10/22.
 */


import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class LoadNewsListTask extends AsyncTask<String, Void, List<NetEase>> {
    @Override
    protected List<NetEase> doInBackground(String... params) {
        String u = params[0];
        List<NetEase> neteaseNews = new ArrayList<NetEase>();
        try {
            URL url = new URL(u);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                InputStream in = con.getInputStream();
                byte[] buff = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = in.read(buff)) != -1) {
                    bos.write(buff, 0, len);
                }
                final String result = new String(bos.toByteArray());
                Gson gson = new Gson();
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray array = obj.getJSONArray("T1348647909107");
                    for (int i = 0; i < array.length(); i++) {
                        NetEase netEase = gson.fromJson(array.get(i).toString(), NetEase.class);
                        neteaseNews.add(netEase);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return neteaseNews;
    }

    @Override
    protected void onPostExecute(List<NetEase> netEases) {
        if (loadListener != null) {
            loadListener.setListToAdapter(netEases);
        }
    }

    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    private LoadListener loadListener;

    public interface LoadListener {
        void setListToAdapter(List<NetEase> netEases);
    }
}
