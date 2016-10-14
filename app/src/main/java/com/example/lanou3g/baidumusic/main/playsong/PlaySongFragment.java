package com.example.lanou3g.baidumusic.main.playsong;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.bean.SongTimeEvent;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.bean.MainSongListBean;
import com.example.lanou3g.baidumusic.tools.Tools;
import com.example.lanou3g.baidumusic.values.StringVlaues;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/4.
 */
public class PlaySongFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mIv_back;
    private SeekBar mSb;
    private ImageView mIv_mode;
    private ImageView mIv_prev;
    private ImageView mIv_play;
    private ImageView mIv_next;
    private Boolean isPlaying;
    private TextView mTv_songTime;
    private TextView mTv_pastTime;
    private int songTime;
    private int pastTime;
    private PlaySongBean mPlaySongBean;
    private List<MainSongListBean> mSongListBeen;
    private PlayingSongListener mPlayingSongListener;
    private ViewPager mVp;
    private ImageView mIv_share;
    private int playMode;

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    public void setPlayingSongListener(PlayingSongListener playingSongListener) {
        mPlayingSongListener = playingSongListener;
    }

    public void setSongListBeen(List<MainSongListBean> songListBeen) {
        mSongListBeen = songListBeen;
    }

    public void setPlaySongBean(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_play_song;
    }

    @Override
    protected void initView() {
        mIv_back = bindView(R.id.iv_back_playsong);
        mSb = bindView(R.id.sb_play);
        mIv_mode = bindView(R.id.iv_playsong_mode);
        mIv_prev = bindView(R.id.iv_playsong_prev);
        mIv_play = bindView(R.id.iv_playsong_play);
        mIv_next = bindView(R.id.iv_playsong_next);
        mTv_songTime = bindView(R.id.tv_songtime_play);
        mTv_pastTime = bindView(R.id.tv_pasttime_play);
        mVp = bindView(R.id.vp_playsong);
        mIv_share = bindView(R.id.iv_playsong_share);
    }

    @Override
    protected void initData() {
        mVp.setOffscreenPageLimit(2);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.anim_null, R.anim.fragment_playsong_slide_out);
                transaction.remove(PlaySongFragment.this);
                transaction.commit();
            }
        });
        EventBus.getDefault().register(this);
        if (isPlaying) {
            mIv_play.setImageResource(R.mipmap.bt_notificationbar_pause);
        } else {
            mIv_play.setImageResource(R.mipmap.bt_notificationbar_play);
        }
        updatePlayMode();
        FragmentPlaySongFirst fragmentPlaySongFirst = new FragmentPlaySongFirst();
        FragmentPlaySongSecond fragmentPlaySongSecond = new FragmentPlaySongSecond();
        FragmentPlaySongThird fragmentPlaySongThird = new FragmentPlaySongThird();
        fragmentPlaySongSecond.setPlaySongBean(mPlaySongBean);
        fragmentPlaySongThird.setPlaySongBean(mPlaySongBean);
        fragmentPlaySongThird.setPlayingSongListener(mPlayingSongListener);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragmentPlaySongFirst);
        fragments.add(fragmentPlaySongSecond);
        fragments.add(fragmentPlaySongThird);
        PlaySongFragmentAdapter adapter = new PlaySongFragmentAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        mVp.setAdapter(adapter);
        mVp.setCurrentItem(1);
        ZoomOutPageTransformer transformer = new ZoomOutPageTransformer(fragmentPlaySongSecond);
        mVp.setPageTransformer(true, transformer);
        mIv_play.setOnClickListener(this);
        mIv_next.setOnClickListener(this);
        mIv_prev.setOnClickListener(this);
        mIv_share.setOnClickListener(this);
        mIv_mode.setOnClickListener(this);
        mSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayingSongListener.seekTo(songTime * progress / seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d("PlaySongFragment", "position:" + position);
//                Log.d("PlaySongFragment", "positionOffset:" + positionOffset);
//                Log.d("PlaySongFragment", "positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_playsong_play:
                mPlayingSongListener.isPlay(isPlaying);
                break;
            case R.id.iv_playsong_next:
                mPlayingSongListener.playingNext();
                break;
            case R.id.iv_playsong_prev:
                mPlayingSongListener.playingPrev();
                break;
            case R.id.iv_playsong_share:
                Tools.showShare();
                break;
            case R.id.iv_playsong_mode:
                playMode++;
                if (playMode > 4){
                    playMode = 1;
                }
                updatePlayMode();
                mPlayingSongListener.settingMode(playMode);
                break;
            default:
                break;
        }
    }

    public void update() {
        mTv_pastTime.setText(Tools.getFormatedDateTime(pastTime));
        mTv_songTime.setText(Tools.getFormatedDateTime(songTime));
        mSb.setProgress(mSb.getMax() * pastTime / songTime);
    }

    public void updatePlayMode(){
        if (playMode == StringVlaues.PLAY_MODE_LOOP){
            mIv_mode.setImageResource(R.mipmap.bt_list_button_roundplay_normal);
        } else if (playMode == StringVlaues.PLAY_MODE_ORDER){
            mIv_mode.setImageResource(R.mipmap.bt_list_order_normal);
        } else if (playMode == StringVlaues.PLAY_MODE_RANDOM){
            mIv_mode.setImageResource(R.mipmap.bt_list_random_normal);
        } else if (playMode == StringVlaues.PLAY_MODE_SINGLE_LOOP){
            mIv_mode.setImageResource(R.mipmap.bt_list_roundsingle_normal);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTime(SongTimeEvent songTimeEvent){
        pastTime = songTimeEvent.getPastTime();
        songTime = songTimeEvent.getSongTime();
        update();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isPlaying(Boolean playing) {
        if (playing) {
            mIv_play.setImageResource(R.mipmap.bt_notificationbar_pause);
            isPlaying = true;
        } else {
            mIv_play.setImageResource(R.mipmap.bt_notificationbar_play);
            isPlaying = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}