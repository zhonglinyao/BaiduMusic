package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.GsonRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.URLVlaues;
import com.example.lanou3g.baidumusic.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MusicTopFragment extends BaseFragment{

    private ListView lv;
    @Override
    protected int setLayout() {
        return R.layout.fragment_musictop;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_music_top);
        lv.setVerticalScrollBarEnabled(false);
        View footerView = LayoutInflater.from(context).inflate(R.layout.layout_footerview_null, lv, false);
        View v = (View) footerView.findViewById(R.id.footerView);
        ViewGroup.LayoutParams params = v.getLayoutParams();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int h = metrics.heightPixels;
        params.height = (int) (h / 13);
        v.setLayoutParams(params);
        lv.addFooterView(footerView);
    }

    @Override
    protected void initData() {
        final MusicTopAdapter adapter = new MusicTopAdapter(context);
        GsonRequest<MusicTopBean> gsonRequest =
                new GsonRequest<MusicTopBean>(URLVlaues.MUSIC_TOP,
                        MusicTopBean.class,
                        new Response.Listener<MusicTopBean>() {
                            @Override
                            public void onResponse(MusicTopBean response) {
                                ArrayList<MusicTopBean.TopNameBean> list = (ArrayList<MusicTopBean.TopNameBean>) response.getTopName();
                                adapter.setList(list);
                                lv.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
    }
}
