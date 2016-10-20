package com.example.lanou3g.baidumusic.main.playsong;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.bean.SongTimeEvent;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.request.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.lrc.view.ILrcBuilder;
import com.example.lanou3g.baidumusic.main.lrc.view.ILrcViewListener;
import com.example.lanou3g.baidumusic.main.lrc.view.impl.DefaultLrcBuilder;
import com.example.lanou3g.baidumusic.main.lrc.view.impl.LrcRow;
import com.example.lanou3g.baidumusic.main.lrc.view.impl.LrcView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by dllo on 16/10/10.
 */
public class FragmentPlaySongThird extends BaseFragment {

    private LrcView mLrcView;
    private Boolean isPlaying;
    private int pastTime;
    private int songTime;
    private PlaySongBean mPlaySongBean;
    private String lrcStr;
    private List<LrcRow> mRows;
    private ILrcBuilder mBuilder;
    private PlayingSongListener mPlayingSongListener;

    public void setPlayingSongListener(PlayingSongListener playingSongListener) {
        mPlayingSongListener = playingSongListener;
    }

    public void setPlaySongBean(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
    }

    @Override
    protected int setLayout() {
        return R.layout.layout_playsong_thrid;
    }

    @Override
    protected void initView() {
        mLrcView = bindView(R.id.lrc_playsong);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        //解析歌词构造器
        mBuilder = new DefaultLrcBuilder();
        lrcUpdate();
        mLrcView.setListener(new ILrcViewListener() {
            @Override
            public void onLrcSeeked(int newPosition, LrcRow row) {
                mPlayingSongListener.seekTo((int) row.time);
            }
        });
    }
    private void lrcUpdate(){
        StringRequest stringRequest = new StringRequest(mPlaySongBean.getSonginfo().getLrclink(),
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        lrcStr = response;
                        //解析歌词返回LrcRow集合
                        mRows = mBuilder.getLrcRows(lrcStr);
                        //将得到的歌词集合传给mLrcView用来展示
                        mLrcView.setLrc(mRows);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mLrcView.setLrc(null);
            }
        });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(stringRequest);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTime(SongTimeEvent songTimeEvent) {
        pastTime = songTimeEvent.getPastTime();
        songTime = songTimeEvent.getSongTime();
        mLrcView.seekLrcToTime(pastTime);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongInfo(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
        lrcUpdate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
