package com.example.lanou3g.baidumusic;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/9/20.
 */
public class MyApp extends Application{
    private static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
