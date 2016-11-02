package com.example.administrator.jsondemo2.app;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/22.
 * 一定一定一定要到清单注册，清单中的application添加name属性为当前类名
 * 当前应用的一个唯一的全局的上下文对象
 */

public class MyApplication extends Application {
    private static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
    public static MyApplication getApp(){
        return app;
    }
}
