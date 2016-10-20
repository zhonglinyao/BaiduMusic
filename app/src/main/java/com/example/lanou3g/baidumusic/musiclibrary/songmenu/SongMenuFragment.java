package com.example.lanou3g.baidumusic.musiclibrary.songmenu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.bean.HotSongMenuBean;
import com.example.lanou3g.baidumusic.bean.PlaySongMenuEvent;
import com.example.lanou3g.baidumusic.bean.SongMenuBean;
import com.example.lanou3g.baidumusic.request.GsonRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.values.URLVlaues;
import com.example.lanou3g.baidumusic.request.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.musiclibrary.recommend.SongMenuListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class SongMenuFragment extends BaseFragment {

    private RecyclerView rv;
    private TextView tv_now;
    private TextView tv_hot;
    private PlaySongMenuEvent mPlaySongMenuEvent;

    @Override
    protected int setLayout() {
        return R.layout.fragment_songmenu;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.rv_song_menu);
        tv_now = bindView(R.id.tv_now_songmenu);
        tv_hot = bindView(R.id.tv_hot_songmenu);
        tv_hot.setTextColor(context.getResources().getColor(R.color.colorMain));
        tv_now.setTextColor(context.getResources().getColor(R.color.colorHotSongMenuLayoutNor));
    }

    @Override
    protected void initData() {
        final SongMenuAdapter adapter = new SongMenuAdapter(context);
        final GridLayoutManager manager = new GridLayoutManager(context, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
        adapter.setSongMenuListener(new SongMenuListener() {
            Response.ErrorListener mErrorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };

            @Override
            public void callBack(String list_id) {
                GsonRequest<HotSongMenuBean> hotSongMenuBeanGsonRequest =
                        new GsonRequest<>(
                                URLVlaues.getHotSongMenu(list_id),
                                HotSongMenuBean.class,
                                new Response.Listener<HotSongMenuBean>() {
                                    @Override
                                    public void onResponse(HotSongMenuBean response) {
                                        FragmentManager manager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction transaction = manager.beginTransaction();
                                        transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                                        HotSongMenuFragment hotSongMenuFragment = new HotSongMenuFragment();
                                        hotSongMenuFragment.setHotSongMenuBean(response);
                                        transaction.replace(R.id.fl_main, hotSongMenuFragment);
                                        transaction.commit();
                                    }
                                },
                                mErrorListener);
                VolleyRequestQueue.getVolleyRequestQueue().addRequest(hotSongMenuBeanGsonRequest);
            }

            @Override
            public void playCallBack(String list_id) {
                GsonRequest<HotSongMenuBean> hotSongMenuBeanGsonRequest =
                        new GsonRequest<>(
                                URLVlaues.getHotSongMenu(list_id),
                                HotSongMenuBean.class,
                                new Response.Listener<HotSongMenuBean>() {

                                    @Override
                                    public void onResponse(HotSongMenuBean response) {
                                        if (mPlaySongMenuEvent == null) {
                                            mPlaySongMenuEvent = new PlaySongMenuEvent();
                                        }
                                        mPlaySongMenuEvent.setContentBeen(response.getContent());
                                        mPlaySongMenuEvent.setItem(0);
                                        EventBus.getDefault().post(mPlaySongMenuEvent);
                                    }
                                },
                                mErrorListener);
                VolleyRequestQueue.getVolleyRequestQueue().addRequest(hotSongMenuBeanGsonRequest);

            }
        });
        GsonRequest<SongMenuBean> beanGsonRequest = new GsonRequest<SongMenuBean>(
                URLVlaues.SONGMENU_HOT,
                SongMenuBean.class,
                new Response.Listener<SongMenuBean>() {
                    @Override
                    public void onResponse(SongMenuBean response) {
                        List<SongMenuBean.DiyInfoBean> list = response.getDiyInfo();
                        adapter.setList(list);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(beanGsonRequest);
        tv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_hot.setTextColor(context.getResources().getColor(R.color.colorMain));
                tv_now.setTextColor(context.getResources().getColor(R.color.colorHotSongMenuLayoutNor));
                GsonRequest<SongMenuBean> beanGsonRequest = new GsonRequest<SongMenuBean>(
                        URLVlaues.SONGMENU_HOT,
                        SongMenuBean.class,
                        new Response.Listener<SongMenuBean>() {
                            @Override
                            public void onResponse(SongMenuBean response) {
                                List<SongMenuBean.DiyInfoBean> list = response.getDiyInfo();
                                manager.removeAllViews();
                                adapter.setList(list);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                VolleyRequestQueue.getVolleyRequestQueue().addRequest(beanGsonRequest);
            }
        });

        tv_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_now.setTextColor(context.getResources().getColor(R.color.colorMain));
                tv_hot.setTextColor(context.getResources().getColor(R.color.colorHotSongMenuLayoutNor));
                GsonRequest<SongMenuBean> beanGsonRequest = new GsonRequest<SongMenuBean>(
                        URLVlaues.SONGMENU_NEW,
                        SongMenuBean.class,
                        new Response.Listener<SongMenuBean>() {
                            @Override
                            public void onResponse(SongMenuBean response) {
                                List<SongMenuBean.DiyInfoBean> list = response.getDiyInfo();
                                manager.removeAllViews();
                                adapter.setList(list);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                VolleyRequestQueue.getVolleyRequestQueue().addRequest(beanGsonRequest);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
