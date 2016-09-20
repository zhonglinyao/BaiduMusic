package com.example.lanou3g.baidumusic.main;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.live.LiveFragment;
import com.example.lanou3g.baidumusic.mine.MineFragment;
import com.example.lanou3g.baidumusic.musiclibrary.MusicLibraryFragment;
import com.example.lanou3g.baidumusic.singsong.SingSongFragment;

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
    }

    @Override
    protected void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("我的");
        strings.add("乐库");
        strings.add("K歌");
        strings.add("直播");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MineFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(new SingSongFragment());
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
