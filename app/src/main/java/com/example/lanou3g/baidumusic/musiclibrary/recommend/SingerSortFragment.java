package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.GsonRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.URLVlaues;
import com.example.lanou3g.baidumusic.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class SingerSortFragment extends BaseFragment {

    private ListView lv;
    private ViewPager vp;
    private LinearLayout linearLayout;
    private ArrayList<ImageView> imageViews;

    @Override
    protected int setLayout() {
        return R.layout.fragment_singer_sort;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_singer);
        View headView = LayoutInflater.from(context).inflate(R.layout.layout_singer_head, lv,false);
        vp = (ViewPager) headView.findViewById(R.id.vp_singer_head);
        linearLayout = (LinearLayout) headView.findViewById(R.id.ll_singer_head);
        ImageButton ib_back = bindView(R.id.ib_back_singer_sort);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                transaction.remove(SingerSortFragment.this);
                transaction.commit();
            }
        });

        ViewGroup.LayoutParams params = vp.getLayoutParams();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int h = metrics.heightPixels;
        params.height = h / 4;
        vp.setLayoutParams(params);
        lv.addHeaderView(headView);

//        View footerView = LayoutInflater.from(context).inflate(R.layout.layout_footerview, lv, false);
//        int h1 = metrics.heightPixels;
//        params.height = h1 / 8;
//        footerView.setLayoutParams(params);
//        lv.addFooterView(footerView);

        lv.setVerticalScrollBarEnabled(false);

//        manager.getDefaultDisplay().getMetrics(metrics);
//        int height = metrics.heightPixels;
//        params.height = (int) (height / 8);
//        footerView.setLayoutParams(params);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    if (i == position){
                        imageViews.get(i).setBackgroundResource(R.mipmap.ic_dot_default_selected);
                    } else {
                        imageViews.get(i).setBackgroundResource(R.mipmap.ic_dot_default_unselected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initData() {
        final SingerSortHeadAdapter headAdapter = new SingerSortHeadAdapter(context);
        SingerSortAdapter adapter = new SingerSortAdapter(context);
        vp.setAdapter(headAdapter);
        lv.setAdapter(adapter);
        GsonRequest<HotSingerSortBean> hotSingerSortBeanGsonRequest =
                new GsonRequest<HotSingerSortBean>(
                        URLVlaues.SINGER_HEAD,
                        HotSingerSortBean.class,
                        new Response.Listener<HotSingerSortBean>() {
                            @Override
                            public void onResponse(HotSingerSortBean response) {
                                List<HotSingerSortBean.ArtistBean> artistBeen = response.getArtist();
                                ArrayList<List<HotSingerSortBean.ArtistBean>> lists = new ArrayList<>();
                                int size = artistBeen.size() / 3;
                                for (int i = 0; i < size; i++) {
                                    int a = i * 3;
                                    lists.add(artistBeen.subList(a, a + 3));
                                }
                                Log.d("SingerSortFragment", "lists.size():" + lists.size());
                                headAdapter.setLists(lists);
                                imageViews = new ArrayList<>();
                                for (int i = 0; i < 4; i++) {
                                    ImageView imageView = new ImageView(context);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 10);
                                    params.setMargins(5, 0, 5, 0);
                                    imageView.setLayoutParams(params);
                                    if (0 == i){
                                        imageView.setBackgroundResource(R.mipmap.ic_dot_default_selected);
                                    } else {
                                        imageView.setBackgroundResource(R.mipmap.ic_dot_default_unselected);
                                    }
                                    imageViews.add(imageView);
                                    linearLayout.addView(imageViews.get(i));
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(hotSingerSortBeanGsonRequest);
    }
}
