package com.yellon.treelistview.utils;

import com.yellon.treelistview.DemoApplication;

/**
 * @author qiuyanlong(email:276644135@qq.com)
 * @date 2016-08-22 18:13
 * @package utils
 * @description Utils
 * @params 工具类
 */
public class SysUtils {

    /**
     * 获取主线程id
     *
     * @return
     */
    public static long getMainThreadId() {
        return DemoApplication.getMainThreadId();
    }

    /**
     * 判断当前是否是主线程
     *
     * @return
     */
    public static boolean isUIThread(){
        return android.os.Process.myTid() == getMainThreadId();
    }
} 