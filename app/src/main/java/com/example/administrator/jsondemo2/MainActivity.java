package com.example.administrator.jsondemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.jsondemo2.adapter.NetEaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    String str="http://118.244.212.82:9092/newsClient/news_sort?ver=0&imei=0";
    String netease = "http://c.m.163.com/nc/article/list/T1348647909107/0-20.html";
    @BindView(R.id.listView)
    ListView mListView;
    NetEaseAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdapter=new NetEaseAdapter();
        mListView= (ListView) findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        getDataByUrl(netease);
    }

    private void getDataByUrl(String str) {
        LoadNewsListTask task = new LoadNewsListTask();
        task.setLoadListener(new LoadNewsListTask.LoadListener() {
            @Override
            public void setListToAdapter(List<NetEase> netEases) {
                //添加到适配器，
                mAdapter.setDataList(netEases);
                mAdapter.notifyDataSetChanged();
            }
        });
        task.execute(str);

    }


}
