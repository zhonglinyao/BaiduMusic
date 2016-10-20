package com.example.lanou3g.baidumusic.main.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.MainSongListBean;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.bean.SongTimeEvent;
import com.example.lanou3g.baidumusic.main.playsong.Player;
import com.example.lanou3g.baidumusic.values.StringVlaues;

/**
 * Created by dllo on 16/9/30.
 */
public class PlaySongService extends Service {
    private PlaySongBinder mBinder = new PlaySongBinder();
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private RemoteViews mRemoteViews;
    private Boolean mIsPlaying;

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        builder.setSmallIcon(R.mipmap.default_skin_thumbnail).
                setContent(mRemoteViews).
                setPriority(Notification.PRIORITY_HIGH).
                setOngoing(true).
                setStyle(style);
        style.setBuilder(builder);
        mNotification = builder.build();
        mNotification.bigContentView = mRemoteViews;
        mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mNotificationManager.cancelAll();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }

    public class PlaySongBinder extends Binder {
        private Player mPlayer = Player.getmPlayer();
        private PlaySongBean mPlaySongBean;

        public void playStart() {
            mIsPlaying = true;
            mRemoteViews.setImageViewResource(R.id.iv_play_notification, R.mipmap.bt_notificationbar_pause);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
            mPlayer.playStart();
        }

        public void playPause() {
            mIsPlaying = false;
            mRemoteViews.setImageViewResource(R.id.iv_play_notification, R.mipmap.bt_notificationbar_play);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
            mPlayer.playPause();
        }

        public void playStop() {
            mPlayer.playStop();
        }

        public void play(PlaySongBean playSongBean) {
            mIsPlaying = true;
            mPlaySongBean = playSongBean;
            mPlayer.playURL(playSongBean);
            updateNotificationInfo(playSongBean);
            mRemoteViews.setImageViewResource(R.id.iv_play_notification, R.mipmap.bt_notificationbar_pause);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
        }

        public void playTo(int progress) {
            mPlayer.seekTo(progress);
        }

        public void updateNotificationInfo(PlaySongBean playSongBean) {
            mPlaySongBean = playSongBean;
            mRemoteViews.setTextViewText(R.id.tv_title_notification, playSongBean.getSonginfo().getTitle());
            mRemoteViews.setTextViewText(R.id.tv_author_notification, playSongBean.getSonginfo().getAuthor());
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
        }

        public void updateNotificationImg(Bitmap bitmap) {
            mRemoteViews.setImageViewBitmap(R.id.iv_notification, bitmap);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
        }

        public SongTimeEvent songTime() {
            return mPlayer.songTime();
        }

        public void playLocal(MainSongListBean mainSongListBean) {
            mIsPlaying = true;
            mPlayer.playLocal(mainSongListBean);
            mRemoteViews.setImageViewResource(R.id.iv_play_notification, R.mipmap.bt_notificationbar_pause);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
        }
    }

}
