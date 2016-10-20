package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.bean.MusicTopBean;
import com.example.lanou3g.baidumusic.bean.MusicTopSongListBean;
import com.example.lanou3g.baidumusic.bean.PlayMusicTopEvent;
import com.example.lanou3g.baidumusic.request.GsonRequest;
import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.values.URLVlaues;
import com.example.lanou3g.baidumusic.request.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class MusicTopFragment extends BaseFragment{

    private ListView lv;
    private MusicTopAdapter mAdapter;
    private MusicTopBean.TopNameBean mNameBean;
    private MusicTopSongListFragment mMusicTopSongListFragment;
    private MusicTopSongListBean mMusicTopSongListBean;
    private PlayMusicTopEvent mPlayMusicTopEvent;

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
        params.height = (int) (MyApp.getWindowHeight() / 13);
        v.setLayoutParams(params);
        lv.addFooterView(footerView);
    }

    @Override
    protected void initData() {
        mAdapter = new MusicTopAdapter(context);
        mAdapter.setMusicTopListener(new MusicTopListener() {
            @Override
            public void playAllListener(final MusicTopBean.TopNameBean topNameBean) {
                if (mNameBean != null && mNameBean.getType() == topNameBean.getType()){
                    PlayMusicTopEvent playMusicTopEvent = new PlayMusicTopEvent();
                    playMusicTopEvent.setItem(0);
                    playMusicTopEvent.setSongListBeen(mMusicTopSongListBean.getSong_list());
                    EventBus.getDefault().post(playMusicTopEvent);
                } else {
                    mNameBean = topNameBean;
                    GsonRequest<MusicTopSongListBean> gsonRequest =
                            new GsonRequest<>(
                                    URLVlaues.getMusictopSonglist(topNameBean.getType()),
                                    MusicTopSongListBean.class,
                                    new Response.Listener<MusicTopSongListBean>() {

                                        @Override
                                        public void onResponse(MusicTopSongListBean response) {
                                            mMusicTopSongListBean = response;
                                            if (mPlayMusicTopEvent == null){
                                                mPlayMusicTopEvent = new PlayMusicTopEvent();
                                            }
                                            mPlayMusicTopEvent.setItem(0);
                                            mPlayMusicTopEvent.setSongListBeen(mMusicTopSongListBean.getSong_list());
                                            EventBus.getDefault().post(mPlayMusicTopEvent);
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

            @Override
            public void jumpListener(MusicTopBean.TopNameBean topNameBean) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction transaction = manager.beginTransaction();
                if (mNameBean != null && mNameBean.getType() == topNameBean.getType()){
                    if (mMusicTopSongListFragment == null){
                        mMusicTopSongListFragment = new MusicTopSongListFragment();
                    }
                    mMusicTopSongListFragment.setMusicTopSongListBean(mMusicTopSongListBean);
                    transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                    transaction.add(R.id.fl_main, mMusicTopSongListFragment);
                    transaction.commit();
                } else {
                    mNameBean = topNameBean;
                    GsonRequest<MusicTopSongListBean> gsonRequest =
                            new GsonRequest<>(
                                    URLVlaues.getMusictopSonglist(topNameBean.getType()),
                                    MusicTopSongListBean.class,
                                    new Response.Listener<MusicTopSongListBean>() {
                                        @Override
                                        public void onResponse(MusicTopSongListBean response) {
                                            mMusicTopSongListBean = response;
                                            if (mMusicTopSongListFragment == null){
                                                mMusicTopSongListFragment = new MusicTopSongListFragment();
                                            }
                                            mMusicTopSongListFragment.setMusicTopSongListBean(mMusicTopSongListBean);
                                            transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                                            transaction.add(R.id.fl_main, mMusicTopSongListFragment);
                                            transaction.commit();
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
        });
        GsonRequest<MusicTopBean> gsonRequest =
                new GsonRequest<>(URLVlaues.MUSIC_TOP,
                        MusicTopBean.class,
                        new Response.Listener<MusicTopBean>() {
                            @Override
                            public void onResponse(MusicTopBean response) {
                                ArrayList<MusicTopBean.TopNameBean> list = (ArrayList<MusicTopBean.TopNameBean>) response.getTopName();
                                mAdapter.setList(list);
                                lv.setAdapter(mAdapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
