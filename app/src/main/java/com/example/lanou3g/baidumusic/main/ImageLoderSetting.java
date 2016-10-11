package com.example.lanou3g.baidumusic.main;

import android.graphics.Bitmap;

import com.example.lanou3g.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by dllo on 16/10/11.
 */
public class ImageLoderSetting {
    private static DisplayImageOptions options;
    private static DisplayImageOptions mvOptions;
    private static DisplayImageOptions musicTopSongListOptions;
    private static ImageLoderSetting setting;

//    public static ImageLoderSetting getSetting() {
//        if (setting == null){
//            synchronized (ImageLoderSetting.class){
//                if (setting == null){
//                    setting = new ImageLoderSetting();
//                }
//            }
//        }
//        return setting;
//    }
//
//    public ImageLoderSetting() {
//
//    }

    public static DisplayImageOptions getOptions() {
        if (options == null) {
            synchronized (ImageLoderSetting.class) {
                if (options == null) {
                    options = new DisplayImageOptions
                            .Builder()
                            .showImageForEmptyUri(R.mipmap.default_artist)
                            .showImageOnLoading(R.mipmap.default_artist)
                            .bitmapConfig(Bitmap.Config.ARGB_8888)
                            .imageScaleType(ImageScaleType.EXACTLY)
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .considerExifParams(true)
                            .build();
                }
            }
        }
        return options;
    }

    public static DisplayImageOptions getMvOptions() {
        if (mvOptions == null) {
            synchronized (ImageLoderSetting.class) {
                if (mvOptions == null) {
                    mvOptions = new DisplayImageOptions
                            .Builder()
                            .showImageForEmptyUri(R.mipmap.default_mv)
                            .showImageOnLoading(R.mipmap.default_mv)
                            .bitmapConfig(Bitmap.Config.ARGB_8888)
                            .imageScaleType(ImageScaleType.EXACTLY)
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .considerExifParams(true)
                            .build();
                }
            }
        }
        return mvOptions;
    }

    public static DisplayImageOptions getMusicTopSongListOptions() {
        if (musicTopSongListOptions == null) {
            synchronized (ImageLoderSetting.class) {
                if (musicTopSongListOptions == null) {
                    musicTopSongListOptions = new DisplayImageOptions
                            .Builder()
                            .bitmapConfig(Bitmap.Config.ARGB_8888)
                            .imageScaleType(ImageScaleType.EXACTLY)
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .considerExifParams(true)
                            .build();
                }
            }
        }
        return musicTopSongListOptions;
    }
}
