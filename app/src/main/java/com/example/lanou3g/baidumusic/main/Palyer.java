package com.example.lanou3g.baidumusic.main;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dllo on 16/9/30.
 */
public class Palyer implements MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener {

    public MediaPlayer mPlayer;
    private Timer mTimer = new Timer();


    public Palyer() {
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnBufferingUpdateListener(this);
            mPlayer.setOnPreparedListener(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pause() {
        mPlayer.pause();
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }



    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mPlayer == null)
                return;
        }
    };

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
