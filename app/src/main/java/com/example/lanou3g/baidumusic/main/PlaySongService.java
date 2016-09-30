package com.example.lanou3g.baidumusic.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.URLVlaues;
import com.example.lanou3g.baidumusic.VolleyRequestQueue;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dllo on 16/9/30.
 */
public class PlaySongService extends Service {
    private PlaySongBinder mBinder = new PlaySongBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        playSongNotification();
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

        private MediaPlayer mPlayer;

        public void playSongList(ArrayList<String> songList) {
            Log.d("qq", "playSongList() called with: " + "songList = [" + songList + "]");
            PlaySongGsonRequest<PlaySongBean> gsonRequest =
                    new PlaySongGsonRequest<PlaySongBean>(
                            URLVlaues.getPlaySong(songList.get(0)),
                            PlaySongBean.class,
                            new Response.Listener<PlaySongBean>() {
                                @Override
                                public void onResponse(PlaySongBean response) {
                                    Log.d("PlaySongBinder", response.getBitrate().getFile_link());
                                    mPlayer = new MediaPlayer();
                                    try {
                                        mPlayer.setDataSource(response.getBitrate().getFile_link());
                                        mPlayer.prepare();
                                        mPlayer.start();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (IllegalArgumentException e){
                                        e.printStackTrace();
                                    }

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

    private void playSongNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.layout_notification);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        builder.setSmallIcon(R.mipmap.default_skin_thumbnail).
                setContent(view).
                setPriority(Notification.PRIORITY_HIGH).
//                setOngoing(true).
        setStyle(style);
        style.setBuilder(builder);
        Notification notification = builder.build();
        notification.bigContentView = view;
        manager.notify(101, notification);
    }
}
