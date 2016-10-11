package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.example.lanou3g.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class CarouseAdapter extends PagerAdapter {
    private ArrayList<ImageView> views;
    private List<RecommendBean.ResultBean.FocusBean.FocusResultBean> focusResultBeen;
    private Context context;
    private final DisplayImageOptions options;

    public CarouseAdapter(Context context) {
        this.context = context;
        views = new ArrayList<>();
        options = new DisplayImageOptions
               .Builder()
               .showImageForEmptyUri(R.mipmap.default_mv)
               .showImageOnLoading(R.mipmap.default_mv)
               .cacheInMemory(true)
               .cacheOnDisk(true)
               .considerExifParams(true)
               .build();
    }

    public void setFocusResultBeen(List<RecommendBean.ResultBean.FocusBean.FocusResultBean> focusResultBeen) {
        this.focusResultBeen = focusResultBeen;
        views.clear();

        for (int i = 0; i < focusResultBeen.size(); i++) {
            ImageView view = new ImageView(context);
            ImageLoader.getInstance().displayImage(focusResultBeen.get(i).getRandpic(), view, options);
            views.add(view);
        }
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
        if (position < 0) {
            position += views.size();
        }

        ImageView view = views.get(position);
        ViewParent parent = view.getParent();
        if (parent != null) {
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
