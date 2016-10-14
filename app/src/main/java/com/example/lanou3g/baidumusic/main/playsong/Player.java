package com.example.lanou3g.baidumusic.main.playsong;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.bean.SongTimeEvent;
import com.example.lanou3g.baidumusic.tools.ThreadTool;
import com.example.lanou3g.baidumusic.values.StringVlaues;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by dllo on 16/9/30.
 */
public class Player implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private static Player mPlayer;
    private  MediaPlayer mMediaPlayer;
    private PlaySongBean mPlaySongBean;
    private Boolean isPlaying = false;
    private SongTimeEvent mSongTimeEvent = new SongTimeEvent();

    public static Player getmPlayer() {
        if (mPlayer == null) {
            synchronized (Player.class) {
                if (mPlayer == null) {
                    mPlayer = new Player();
                }
            }
        }
        return mPlayer;
    }


    public PlaySongBean getPlaySongBean() {
        return mPlaySongBean;
    }

    private Player() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(this);
    }

    public void playURL(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(playSongBean.getBitrate().getFile_link());
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playStart() {
        isPlaying = true;
        EventBus.getDefault().post(isPlaying);
        mMediaPlayer.start();
        ThreadTool.getInstance().getThreadPoolExecutor().execute(mRunnable);
    }

    public void playPause() {
        isPlaying = false;
        EventBus.getDefault().post(isPlaying);
        mMediaPlayer.pause();
    }

    public void playStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void seekTo(int ms) {
        mMediaPlayer.seekTo(ms);
    }

    public SongTimeEvent songTime(){
        mSongTimeEvent.setPastTime(mMediaPlayer.getCurrentPosition());
        mSongTimeEvent.setSongTime(mMediaPlayer.getDuration());
        return mSongTimeEvent;
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while (isPlaying) {
                mSongTimeEvent.setPastTime(mMediaPlayer.getCurrentPosition());
                mSongTimeEvent.setSongTime(mMediaPlayer.getDuration());
                EventBus.getDefault().post(mSongTimeEvent);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPlaying = true;
        EventBus.getDefault().post(isPlaying);
        mp.start();
        ThreadTool.getInstance().getThreadPoolExecutor().execute(mRunnable);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d("Player", "qq");
        playPause();
        EventBus.getDefault().post(StringVlaues.PLAY_NEXT);
    }
}
