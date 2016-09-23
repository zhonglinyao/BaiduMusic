package com.example.lanou3g.baidumusic;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration
                        .Builder(this)
                        .threadPoolSize(3).diskCacheFileCount(100)
                        .build();
        ImageLoader.getInstance().init(configuration);
    }
}
