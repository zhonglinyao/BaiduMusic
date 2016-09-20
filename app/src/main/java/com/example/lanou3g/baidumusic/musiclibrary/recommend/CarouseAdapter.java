package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class CarouseAdapter extends PagerAdapter{
    private ArrayList<SimpleDraweeView> views = new ArrayList<>();

    public void setViews(ArrayList<SimpleDraweeView> views) {
        this.views = views;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size() * 100;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= views.size();
        if (position < 0){
            position += views.size();
        }

        ImageView view = views.get(position);
        ViewParent parent = view.getParent();
        if (parent != null){
            ViewGroup vp = (ViewGroup) parent;
            vp.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
