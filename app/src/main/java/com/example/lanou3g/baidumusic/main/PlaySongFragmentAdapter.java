package com.example.lanou3g.baidumusic.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/10.
 */
public class PlaySongFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    public PlaySongFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
