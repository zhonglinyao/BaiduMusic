package com.example.lanou3g.baidumusic.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */

public class MainAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> strings = new ArrayList<>();

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
