package com.example.lanou3g.baidumusic.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.URLVlaues;
import com.example.lanou3g.baidumusic.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.dynamic.DynamicFragment;
import com.example.lanou3g.baidumusic.live.LiveFragment;
import com.example.lanou3g.baidumusic.mine.MineFragment;
import com.example.lanou3g.baidumusic.musiclibrary.MusicLibraryFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tb;
    private ViewPager vp;
    private RemoteViews mRemoteViews;
    public static PlaySongService.PlaySongBinder mBinder;
    private Intent mIntent;
    private PlaySongConnection mConnection;
    private ImageView mIv_play;
    private NotificationManager mNotificationManager;
    private Notification mNotification;
    private TextView mTv_songtitle;
    private TextView mTv_author;
    private ImageView mIv_next;
    private ImageView mIv_list;
    private RelativeLayout mRl_play;
    private ImageView mIv_main;
    private DisplayImageOptions mOptions;
    private SonglistFragment mSonglistFragment;
    private Boolean listShowing = false;
    private ArrayList<MainSongListBean> mMainSongListBeen = new ArrayList<>();
    private Boolean mIsPlaying = false;
    private PlaySongBean mPlaySongBean;
    private int item;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        mRemoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        builder.setSmallIcon(R.mipmap.default_skin_thumbnail).
                setContent(mRemoteViews).
                setPriority(Notification.PRIORITY_HIGH).
//                setOngoing(true).
        setStyle(style);
        style.setBuilder(builder);
        mNotification = builder.build();
        mNotification.bigContentView = mRemoteViews;
        mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);

        tb = bindView(R.id.tb_main);
        vp = bindView(R.id.vp_main);
        mIv_play = bindView(R.id.iv_play_main);
        mTv_songtitle = bindView(R.id.tv_songtitle_main);
        mTv_author = bindView(R.id.tv_author_main);
        mIv_next = bindView(R.id.iv_next_main);
        mIv_list = bindView(R.id.iv_song_list_main);
        mRl_play = bindView(R.id.rl_playing);
        mIv_main = bindView(R.id.img_main);
        ViewGroup.LayoutParams params = mRl_play.getLayoutParams();
        params.height = MyApp.getWindowHeight() / 13;
        mRl_play.setLayoutParams(params);
    }

    @Override
    protected void initData() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add(StringVlaues.FRAGMENT_MINE);
        strings.add(StringVlaues.FRAGMENT_MUSIC);
        strings.add(StringVlaues.FRAGMENT_DYNAMIC);
        strings.add(StringVlaues.FRAGMENT_LIVE);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MineFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(new DynamicFragment());
        fragments.add(new LiveFragment());
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setStrings(strings);
        mainAdapter.setFragments(fragments);
        vp.setAdapter(mainAdapter);
        tb.setupWithViewPager(vp);
        tb.setSelectedTabIndicatorHeight(0);
        tb.setTabTextColors(Color.argb(140, 255, 255, 255), Color.WHITE);
        vp.setCurrentItem(1);
        mIntent = new Intent(this, PlaySongService.class);
        mConnection = new PlaySongConnection();
        startService(mIntent);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);

        mIv_play.setOnClickListener(this);
        mIv_next.setOnClickListener(this);
        mIv_list.setOnClickListener(this);
        mRl_play.setOnClickListener(this);

        mOptions = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.default_skin_thumbnail)
                .showImageOnLoading(R.mipmap.default_skin_thumbnail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play_main:
                if (mIsPlaying) {
                    mBinder.playPause();
                } else {
                    mBinder.playStart();
                }
                break;
            case R.id.iv_next_main:
                item++;
                playingSong();
                break;
            case R.id.iv_song_list_main:
                if (!listShowing) {
                    listShowing = true;
                    showSongList(listShowing);
                } else {
                    listShowing = false;
                    showSongList(listShowing);
                }
                break;
            case R.id.rl_playing:
                FragmentManager playSongManager = getSupportFragmentManager();
                FragmentTransaction transaction = playSongManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_playsong_slide_in, R.anim.anim_null);
                PlaySongFragment playSongFragment = new PlaySongFragment();
                playSongFragment.setPlaying(mIsPlaying);
                playSongFragment.setPlaySongBean(mPlaySongBean);
                playSongFragment.setSongListBeen(mMainSongListBeen);
                playSongFragment.setPlayingSongListener(mPlayingSongListener);
                transaction.add(R.id.fl_all_main, playSongFragment);
                transaction.commit();
                break;
            default:
                break;
        }
    }

    PlayingSongListener mPlayingSongListener = new PlayingSongListener() {
        @Override
        public void isPlay(Boolean isPlaying) {
            if (isPlaying) {
                mBinder.playPause();
            } else {
                mBinder.playStart();
            }
        }

        @Override
        public void playingNext() {
            playNext();
        }

        @Override
        public void playingPrev() {
            playPrev();
        }
    };

    ShowSongListListener mShowSongListListener = new ShowSongListListener() {
        @Override
        public void showBackListener(Boolean isShow) {
            listShowing = isShow;
        }

        @Override
        public void playItem(List<MainSongListBean> mainSongListBeen, int position) {
            mMainSongListBeen = (ArrayList<MainSongListBean>) mainSongListBeen;
            item = position;
            playingSong();
        }
    };

    private void showSongList(Boolean isShow) {
        if (mSonglistFragment == null) {
            mSonglistFragment = new SonglistFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (isShow) {
            mSonglistFragment.setSongList(mShowSongListListener);
            mSonglistFragment.setSongListBeen(mMainSongListBeen);
            mSonglistFragment.setPlaySongBean(mPlaySongBean);
            transaction.setCustomAnimations(R.anim.fragment_songlist_slide_in, R.anim.anim_null);
            transaction.replace(R.id.fl_main, mSonglistFragment);
        } else {
            transaction.setCustomAnimations(R.anim.anim_null, R.anim.fragment_songlist_slide_out);
            transaction.remove(mSonglistFragment);
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unbindService(mConnection);
    }

    public void updatePlaySongInfo() {
        mTv_songtitle.setText(mPlaySongBean.getSonginfo().getTitle());
        mTv_author.setText(mPlaySongBean.getSonginfo().getAuthor());
        mRemoteViews.setTextViewText(R.id.tv_title_notification, mPlaySongBean.getSonginfo().getTitle());
        mRemoteViews.setTextViewText(R.id.tv_author_notification, mPlaySongBean.getSonginfo().getAuthor());
        ImageLoader.getInstance().displayImage(
                mPlaySongBean.getSonginfo().getPic_big(),
                mIv_main,
                mOptions,
                new SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        super.onLoadingCancelled(imageUri, view);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        mRemoteViews.setImageViewBitmap(R.id.iv_notification, loadedImage);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getplayNext(Integer i) {
        Boolean b = false;
        EventBus.getDefault().post(b);
        if (i == StringVlaues.PLAY_NEXT) {
            playNext();
        }
        if (i == StringVlaues.PLAY_PREV) {
            playPrev();
        }
    }

    public void playNext() {
        item++;
        if (item >= mMainSongListBeen.size()) {
            return;
        } else {
            playingSong();
        }
    }

    public void playPrev() {
        item--;
        if (item < 0) {
            return;
        } else {
            playingSong();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongMenu(PlaySongMenuEvent playSongMenuEvent) {
        Log.d("MainActivity", "aa");
        item = playSongMenuEvent.getItem();
        mMainSongListBeen.clear();
        for (int i = 0; i < playSongMenuEvent.getContentBeen().size(); i++) {
            MainSongListBean songListBean = new MainSongListBean();
            songListBean.setTitle(playSongMenuEvent.getContentBeen().get(i).getTitle());
            songListBean.setAuthor(playSongMenuEvent.getContentBeen().get(i).getAuthor());
            songListBean.setSong_id(playSongMenuEvent.getContentBeen().get(i).getSong_id());
            mMainSongListBeen.add(songListBean);
        }
        playingSong();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlayMusicTopEvent(PlayMusicTopEvent playMusicTopEvent) {
        Log.d("MainActivity", "bb");
        item = playMusicTopEvent.getItem();
        mMainSongListBeen.clear();
        for (int i = 0; i < playMusicTopEvent.getSongListBeen().size(); i++) {
            MainSongListBean songListBean = new MainSongListBean();
            songListBean.setTitle(playMusicTopEvent.getSongListBeen().get(i).getTitle());
            songListBean.setAuthor(playMusicTopEvent.getSongListBeen().get(i).getAuthor());
            songListBean.setSong_id(playMusicTopEvent.getSongListBeen().get(i).getSong_id());
            mMainSongListBeen.add(songListBean);
        }
        playingSong();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isPlaying(Boolean playing) {
        if (playing) {
            mIv_play.setImageResource(R.mipmap.bt_minibar_pause_normal);
            mRemoteViews.setImageViewResource(R.id.iv_play_notification, R.mipmap.bt_notificationbar_pause);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
            mIsPlaying = true;
        } else {
            mIv_play.setImageResource(R.mipmap.bt_minibar_play_normal);
            mRemoteViews.setImageViewResource(R.id.iv_play_notification, R.mipmap.bt_notificationbar_play);
            mNotificationManager.notify(StringVlaues.PLAY_NOTIFICATION, mNotification);
            mIsPlaying = false;
        }
    }

    public void playingSong() {
        PlaySongGsonRequest<PlaySongBean> playSongGsonRequest =
                new PlaySongGsonRequest<PlaySongBean>(
                        URLVlaues.getPlaySong(mMainSongListBeen.get(item).getSong_id()),
                        PlaySongBean.class,
                        new Response.Listener<PlaySongBean>() {
                            @Override
                            public void onResponse(PlaySongBean response) {
                                try {
                                    if (response.getBitrate().getFile_link() != null) {
                                        mBinder.play(response);
                                        mPlaySongBean = response;
                                        updatePlaySongInfo();
                                        EventBus.getDefault().post(response);
                                    }
                                } catch (Exception e) {
                                    playNext();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
        VolleyRequestQueue.getVolleyRequestQueue().addRequest(playSongGsonRequest);
    }

    class PlaySongConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (PlaySongService.PlaySongBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
