package com.example.lanou3g.baidumusic.main;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.live.LiveFragment;
import com.example.lanou3g.baidumusic.mine.MineFragment;
import com.example.lanou3g.baidumusic.musiclibrary.MusicLibraryFragment;
import com.example.lanou3g.baidumusic.dynamic.DynamicFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.tb_main);
        vp = bindView(R.id.vp_main);
        RelativeLayout rl = bindView(R.id.rl_main);
        ViewGroup.LayoutParams params = rl.getLayoutParams();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        params.height = (int) (width / 7);
        rl.setLayoutParams(params);
    }

    @Override
    protected void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("我的");
        strings.add("音乐");
        strings.add("动态");
        strings.add("直播");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MineFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new LiveFragment());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setStrings(strings);
        mainAdapter.setFragments(fragments);
        vp.setAdapter(mainAdapter);
        tb.setupWithViewPager(vp);
        tb.setSelectedTabIndicatorHeight(0);
        tb.setTabTextColors(Color.argb(140, 255, 255, 255), Color.WHITE);
        vp.setCurrentItem(1);
    }
}
