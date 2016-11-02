package com.example.administrator.jsondemo2.base;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/22.
 */

public abstract class MyBaseAdapter <T> extends BaseAdapter{
    protected List<T> dataList;//

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public MyBaseAdapter() {
        super();
        dataList = new ArrayList<T>();
    }

    public MyBaseAdapter(List<T> dataList) {
        super();
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList == null ? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addData(T t) {
        dataList.add(t);
    }

    public void removeData(T t) {
        dataList.remove(t);
    }

    public void addDataList(List<T> mList) {
        if (mList == null) {
            return;
        }
        dataList.addAll(mList);
    }

    public void removeDataList(List<T> mList) {
        if (mList == null) {
            return;
        }
        dataList.removeAll(mList);
    }

    public void clear() {
        dataList.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //声明一个holder
        BaseHolder holder = null;
        if (convertView == null) {
            holder = getHolder(parent.getContext());
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        // new holder，convertview初始化，绑定holder和convertview，初始化convertview中的id
        // }else{
        // 从convertView中找回原来绑定的holder
        // }
        //holder去设置数据，把当前position位置上的对象的属性设置给convertview视图的控件上
        holder.setData(getItem(position));

        return holder.mRootView;
    }

    protected abstract BaseHolder getHolder(Context context);

    public abstract class BaseHolder {
        protected View mRootView;

        public BaseHolder(View rootView) {
            mRootView = rootView;
            mRootView.setTag(this);
            ButterKnife.bind(this, rootView);
        }


        //设置数据
        public abstract void setData(T item);
    }
}
