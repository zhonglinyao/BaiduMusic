package com.example.lanou3g.baidumusic.musiclibrary.songmenu;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
public class SongMenuFragment extends BaseFragment{

    private RecyclerView rv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_songmenu;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.rv_song_menu);

    }

    @Override
    protected void initData() {
        final SongMenuAdapter adapter = new SongMenuAdapter(context);
        rv.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(context,2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        GsonRequest<SongMenuBean> gsonRequest =
                new GsonRequest<SongMenuBean>(
                        URLVlaues.SONGMENU,
                        SongMenuBean.class,
                        new Response.Listener<SongMenuBean>() {
                            @Override
                            public void onResponse(SongMenuBean response) {
                                ArrayList<SongMenuBean.ContentBean> list = (ArrayList<SongMenuBean.ContentBean>) response.getContent();
                                adapter.setArrayList(list);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
    }
}
