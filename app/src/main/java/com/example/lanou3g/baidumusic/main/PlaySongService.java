package com.example.lanou3g.baidumusic.main;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by dllo on 16/9/30.
 */
public class PlaySongService extends Service {
    private PlaySongBinder mBinder = new PlaySongBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class PlaySongBinder extends Binder {
        private Player mPlayer = Player.getmPlayer();

        public void playStart(){
            mPlayer.playStart();
        }

        public void playPause(){
            mPlayer.playPause();
        }

        public void playStop(){
            mPlayer.playStop();
        }

        public void play(PlaySongBean playSongBean){
            mPlayer.playURL(playSongBean);
        }
    }
}
