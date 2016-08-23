package com.yellon.treelistview;

import android.app.Application;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-22 18:16
 * @package PACKAGE_NAME
 * @description DemoApplication  TODO(界面功能描述)
 * @params TODO(进入界面传参描述)
 */
public class DemoApplication extends Application{

    public static DemoApplication app;
    private static int mMainTheadId;//主线程ID

    public static DemoApplication getApp(){
        return app;
    }

    public static int getMainThreadId() {
        return mMainTheadId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mMainTheadId = android.os.Process.myTid();
    }

}