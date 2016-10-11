package com.example.lanou3g.baidumusic.main;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by dllo on 16/10/11.
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private FragmentPlaySongSecond mFragment;

    public ZoomOutPageTransformer(Fragment fragment) {
        mFragment = (FragmentPlaySongSecond) fragment;
    }

    @SuppressLint("NewApi")
    public void transformPage(View page, float position) {
        if (position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}
