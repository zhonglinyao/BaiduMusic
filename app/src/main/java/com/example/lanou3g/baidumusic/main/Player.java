package com.example.lanou3g.baidumusic.main;

import android.media.AudioManager;
import android.media.MediaPlayer;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by dllo on 16/9/30.
 */
public class Player implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private static Player mPlayer;
    private static MediaPlayer mMediaPlayer;
    private PlaySongBean mPlaySongBean;
    private Boolean isPlaying = false;

    public static Player getmPlayer() {
        if (mPlayer == null ){
            synchronized (Player.class){
                if (mPlayer == null){
                    mPlayer = new Player();
                }
            }
        }
        return mPlayer;
    }

    public static MediaPlayer getmMediaPlayer() {
        return mMediaPlayer;
    }

    public PlaySongBean getPlaySongBean() {
        return mPlaySongBean;
    }

    private Player() {
        if (mMediaPlayer == null){
            mMediaPlayer = new MediaPlayer();
        }
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(this);
    }

    public void playURL(PlaySongBean playSongBean){
        mPlaySongBean = playSongBean;
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(playSongBean.getBitrate().getFile_link());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playStart(){
        isPlaying = true;
        EventBus.getDefault().post(isPlaying);
        mMediaPlayer.start();
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

    public int getDuration(){
        return mMediaPlayer.getDuration();
    }

    public int getCurrentPosition(){
        return mMediaPlayer.getCurrentPosition();
    }

    public void seekTo(int ms){
        mMediaPlayer.seekTo(ms);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPlaying = true;
        EventBus.getDefault().post(isPlaying);
        mp.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        EventBus.getDefault().post(StringVlaues.PLAY_NEXT);
    }
}
