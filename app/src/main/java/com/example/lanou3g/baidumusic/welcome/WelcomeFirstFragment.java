package com.example.lanou3g.baidumusic.welcome;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.LinearLayout;

import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.tools.Tools;
import com.example.lanou3g.baidumusic.main.BaseFragment;

/**
 * Created by dllo on 16/9/20.
 */
public class WelcomeFirstFragment extends BaseFragment{

    private LinearLayout mLl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_welcome_first;
    }

    @Override
    protected void initView() {
        mLl = bindView(R.id.ll_welcome_first);
    }

    @Override
    protected void initData() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.welcome_5820_1, options);
        options.inSampleSize = Tools.calculateInSampleSize(options, MyApp.getWindowWidth(), MyApp.getWindowHeight());
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.welcome_5820_1, options);
        mLl.setBackground(new BitmapDrawable(getResources(), bitmap));
    }

}
