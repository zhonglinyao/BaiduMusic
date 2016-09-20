package com.example.lanou3g.baidumusic.welcome;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class WelcomeActivity extends BaseActivity{

    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.vp_welcome);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new WelcomeFirstFragment());
        fragments.add(new WelcomeSecondFragment());

    }
}
