package com.example.lanou3g.baidumusic.musiclibrary.mv;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.bean.MVBean;
import com.example.lanou3g.baidumusic.request.GsonRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.values.URLVlaues;
import com.example.lanou3g.baidumusic.tools.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.musiclibrary.recommend.RefreshListenerCallBack;

/**
 * Created by dllo on 16/9/19.
 */
public class MVFragment extends BaseFragment {

    private RecyclerView rv;
    private TextView tv_hot;
    private TextView tv_now;
    private MVadapter hotAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mv;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.rv_mv);
        tv_hot = bindView(R.id.tv_hot_mv);
        tv_now = bindView(R.id.tv_now_mv);
        tv_now.setTextColor(context.getResources().getColor(R.color.colorMain));
        tv_hot.setTextColor(context.getResources().getColor(R.color.colorHotSongMenuLayoutNor));
    }

    @Override
    protected void initData() {
        final MVadapter adapter = new MVadapter(context);
        hotAdapter = new MVadapter(context);
        final GridLayoutManager manager = new GridLayoutManager(context, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == rv.getAdapter().getItemCount() - 1)
                {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

        adapter.setRefreshListener(new RefreshListenerCallBack() {
            @Override
            public void refresh(TextView v, final int pageNum, int haveMore, boolean isNow) {
                if (isNow) {
                    if (1 == haveMore) {
                        GsonRequest<MVBean> gsonRequest =
                                new GsonRequest<MVBean>(
                                        URLVlaues.getMvUrl(isNow, pageNum + 1),
                                        MVBean.class,
                                        new Response.Listener<MVBean>() {
                                            @Override
                                            public void onResponse(MVBean response) {
                                                adapter.setHaveMore(response.getResult().getHavemore());
                                                adapter.setPageNum(pageNum + 1);
                                                adapter.setIsNow(true);
                                                View view = rv.getChildAt(rv.getChildCount() - 1);
                                                manager.removeView(view);
                                                adapter.addMvListBean(response.getResult().getMv_list());
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
                    } else {
                        v.setText("没有更多");
                    }
                } else {
                    if (1 == haveMore) {
                        GsonRequest<MVBean> gsonRequest =
                                new GsonRequest<MVBean>(
                                        URLVlaues.getMvUrl(isNow, pageNum + 1),
                                        MVBean.class,
                                        new Response.Listener<MVBean>() {
                                            @Override
                                            public void onResponse(MVBean response) {
                                                adapter.setHaveMore(response.getResult().getHavemore());
                                                adapter.setPageNum(pageNum + 1);
                                                adapter.setIsNow(false);
                                                View view = rv.getChildAt(rv.getChildCount() - 1);
                                                manager.removeView(view);
                                                adapter.addMvListBean(response.getResult().getMv_list());
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
                    } else {
                        v.setText("没有更多");
                    }
                }
            }
        });

        GsonRequest<MVBean> gsonRequest =
                new GsonRequest<MVBean>(
                        URLVlaues.getMvUrl(true, 1),
                        MVBean.class,
                        new Response.Listener<MVBean>() {
                            @Override
                            public void onResponse(MVBean response) {
                                adapter.setHaveMore(response.getResult().getHavemore());
                                adapter.setMvListBeen(response.getResult().getMv_list());
                                adapter.setPageNum(1);
                                adapter.setIsNow(true);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
        tv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_hot.setTextColor(context.getResources().getColor(R.color.colorMain));
                tv_now.setTextColor(context.getResources().getColor(R.color.colorHotSongMenuLayoutNor));
                GsonRequest<MVBean> gsonRequest =
                        new GsonRequest<MVBean>(
                                URLVlaues.getMvUrl(false, 1),
                                MVBean.class,
                                new Response.Listener<MVBean>() {
                                    @Override
                                    public void onResponse(MVBean response) {
                                        manager.removeAllViews();
                                        adapter.setHaveMore(response.getResult().getHavemore());
                                        adapter.setPageNum(1);
                                        adapter.setIsNow(false);
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
        });

        tv_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_now.setTextColor(context.getResources().getColor(R.color.colorMain));
                tv_hot.setTextColor(context.getResources().getColor(R.color.colorHotSongMenuLayoutNor));
                GsonRequest<MVBean> gsonRequest =
                        new GsonRequest<MVBean>(
                                URLVlaues.getMvUrl(true, 1),
                                MVBean.class,
                                new Response.Listener<MVBean>() {
                                    @Override
                                    public void onResponse(MVBean response) {
                                        manager.removeAllViews();
                                        adapter.setHaveMore(response.getResult().getHavemore());
                                        adapter.setPageNum(1);
                                        adapter.setIsNow(true);
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
        });
    }
}
