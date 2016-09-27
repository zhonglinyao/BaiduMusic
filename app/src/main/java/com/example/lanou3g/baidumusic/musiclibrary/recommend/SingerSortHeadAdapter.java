package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class SingerSortHeadAdapter extends PagerAdapter {
    private ArrayList<List<HotSingerSortBean.ArtistBean>> lists;
    private Context context;
    private final DisplayImageOptions options;

    public void setLists(ArrayList<List<HotSingerSortBean.ArtistBean>> lists) {
        Log.d("111", "setLists() called with: " + "lists = [" + lists + "]");
        this.lists = lists;
        notifyDataSetChanged();
    }

    public SingerSortHeadAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.default_live_ic)
                .showImageOnLoading(R.mipmap.default_live_ic)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.three_image_recommend, container, false);
        ImageView imgOne = (ImageView) view.findViewById(R.id.img_one_recommend);
        ImageView imgtwo = (ImageView) view.findViewById(R.id.img_two_recommend);
        ImageView imgthree = (ImageView) view.findViewById(R.id.img_three_recommend);
        TextView textViewOne = (TextView) view.findViewById(R.id.tv_title_one_recommend);
        TextView textViewTwo = (TextView) view.findViewById(R.id.tv_title_two_recommend);
        TextView textViewThree = (TextView) view.findViewById(R.id.tv_title_three_recommend);
        textViewOne.setGravity(Gravity.CENTER);
        textViewTwo.setGravity(Gravity.CENTER);
        textViewThree.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams params = imgOne.getLayoutParams();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        params.width = (int) (width / 3);
        params.height = (int) (width / 3);
        imgOne.setLayoutParams(params);
        imgtwo.setLayoutParams(params);
        imgthree.setLayoutParams(params);
        ImageLoader.getInstance().displayImage(lists.get(position).get(0).getAvatar_middle(), imgOne, options);
        ImageLoader.getInstance().displayImage(lists.get(position).get(1).getAvatar_middle(), imgtwo, options);
        ImageLoader.getInstance().displayImage(lists.get(position).get(2).getAvatar_middle(), imgthree, options);
        textViewOne.setText(lists.get(position).get(0).getName());
        textViewTwo.setText(lists.get(position).get(1).getName());
        textViewThree.setText(lists.get(position).get(2).getName());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
