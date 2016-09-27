package com.example.lanou3g.baidumusic.musiclibrary.mv;

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

/**
 * Created by dllo on 16/9/19.
 */
public class MVFragment extends BaseFragment {

    private RecyclerView rv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mv;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.rv_mv);
    }

    @Override
    protected void initData() {
        final MVadapter adapter = new MVadapter(context);
        rv.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(context,2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        GsonRequest<MVBean> gsonRequest =
                new GsonRequest<MVBean>(
                        URLVlaues.getMvNew(1),
                        MVBean.class,
                        new Response.Listener<MVBean>() {
                            @Override
                            public void onResponse(MVBean response) {
                                adapter.setMvListBeen(response.getResult().getMv_list());
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
