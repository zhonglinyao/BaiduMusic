package com.example.lanou3g.baidumusic.musiclibrary;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.musiclibrary.broadcast.KSongFragment;
import com.example.lanou3g.baidumusic.musiclibrary.musictop.MusicTopFragment;
import com.example.lanou3g.baidumusic.musiclibrary.mv.MVFragment;
import com.example.lanou3g.baidumusic.musiclibrary.recommend.RecommendFragment;
import com.example.lanou3g.baidumusic.musiclibrary.songmenu.SongMenuFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MusicLibraryFragment extends BaseFragment{

    private TabLayout tb;
    public static ViewPager vb;

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_library;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.tb_music_library);
        vb = bindView(R.id.vp_music_library);
    }

    @Override
    protected void initData() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("推荐");
        strings.add("歌单");
        strings.add("榜单");
        strings.add("视频");
        strings.add("K歌");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new SongMenuFragment());
        fragments.add(new MusicTopFragment());
        fragments.add(new MVFragment());
        fragments.add(new KSongFragment());
        MusicLibraryAdapter adapter = new MusicLibraryAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        adapter.setStrings(strings);
        vb.setAdapter(adapter);
        tb.setupWithViewPager(vb);
        tb.setSelectedTabIndicatorColor(Color.argb(255, 0, 200, 255));
        tb.setTabTextColors(Color.argb(150,0,0,0), Color.argb(255,0, 200 , 255));
    }
}
