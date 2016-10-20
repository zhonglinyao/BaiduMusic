package com.example.lanou3g.baidumusic.main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.MainSongListBean;
import com.example.lanou3g.baidumusic.bean.PlayMusicTopEvent;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.bean.PlaySongListEvent;
import com.example.lanou3g.baidumusic.bean.PlaySongMenuEvent;
import com.example.lanou3g.baidumusic.dynamic.DynamicFragment;
import com.example.lanou3g.baidumusic.live.LiveFragment;
import com.example.lanou3g.baidumusic.main.playsong.PlaySongFragment;
import com.example.lanou3g.baidumusic.main.playsong.PlayingSongListener;
import com.example.lanou3g.baidumusic.main.service.PlaySongService;
import com.example.lanou3g.baidumusic.main.songlist.ShowSongListListener;
import com.example.lanou3g.baidumusic.main.songlist.SonglistFragment;
import com.example.lanou3g.baidumusic.mine.MineFragment;
import com.example.lanou3g.baidumusic.musiclibrary.MusicLibraryFragment;
import com.example.lanou3g.baidumusic.request.PlaySongGsonRequest;
import com.example.lanou3g.baidumusic.tools.DBtool;
import com.example.lanou3g.baidumusic.request.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.values.StringVlaues;
import com.example.lanou3g.baidumusic.values.URLVlaues;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tb;
    private ViewPager vp;
    private PlaySongService.PlaySongBinder mBinder;
    private Intent mIntent;
    private PlaySongConnection mConnection;
    private ImageView mIv_play;
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
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private int playMode;
    private PlaySongFragment mPlaySongFragment;
    private Random mRandom = new Random();
    private Boolean firstStart;
//    private Boolean isLocal;
//    private ArrayList<LocalMusicBean> mLocalMusicBeen = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        EventBus.getDefault().register(this);

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
        mPreferences = getSharedPreferences("welcome", MODE_PRIVATE);
        mEditor = mPreferences.edit();
//        isLocal = mPreferences.getBoolean("isLocal", true);
        firstStart = mPreferences.getBoolean("firststart", true);
        playMode = mPreferences.getInt(StringVlaues.playMode, StringVlaues.PLAY_MODE_ORDER);
        item = mPreferences.getInt(StringVlaues.playItem, -2);
        if (-2 != item) {
            DBtool.getmDBtools().querySongList(new DBtool.QueryListener<MainSongListBean>() {
                @Override
                public void onQuert(List<MainSongListBean> list) {
                    if (list == null || list.size() == 0) {
//                        item = 0;
//                        isLocal = true;
//                        mLocalMusicBeen.clear();
//                        mLocalMusicBeen.addAll(Tools.localMusicLoader(getContentResolver()));
//                        for (int i = 0; i < mLocalMusicBeen.size(); i++) {
//                            MainSongListBean mainSongListBean = new MainSongListBean();
//                            mainSongListBean.setTitle(mLocalMusicBeen.get(i).getTitle());
//                            mainSongListBean.setAuthor(mLocalMusicBeen.get(i).getArtist());
//                            mainSongListBean.setSong_id(mLocalMusicBeen.get(i).getUrl());
//                            mMainSongListBeen.add(mainSongListBean);
//                        }
//                        Log.d("MainActivity", "mMainSongListBeen.size():" + mMainSongListBeen.size());
//                        updatePlaySongInfo();
                    } else {
                        mMainSongListBeen.addAll(list);
                        PlaySongGsonRequest<PlaySongBean> gsonRequest =
                                new PlaySongGsonRequest<>(
                                        URLVlaues.getPlaySong(mMainSongListBeen.get(item).getSong_id()),
                                        PlaySongBean.class,
                                        new Response.Listener<PlaySongBean>() {
                                            @Override
                                            public void onResponse(PlaySongBean response) {
                                                mPlaySongBean = response;
                                                updatePlaySongInfo();
                                                mBinder.updateNotificationInfo(response);
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
        } else {

//            Log.d("MainActivity", "22");
//            item = 0;
//            for (int i = 0; i < mLocalMusicBeen.size(); i++) {
//                MainSongListBean mainSongListBean = new MainSongListBean();
//                mainSongListBean.setTitle(mLocalMusicBeen.get(i).getTitle());
//                mainSongListBean.setAuthor(mLocalMusicBeen.get(i).getArtist());
//                mainSongListBean.setSong_id(mLocalMusicBeen.get(i).getUrl());
//                mMainSongListBeen.add(mainSongListBean);
//            }
//            updatePlaySongInfo();
        }

        ShareSDK.initSDK(this);
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
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play_main:
                if (mMainSongListBeen.size() > 0 && mMainSongListBeen != null && mMainSongListBeen.get(0).getSong_id() != null) {
                    if (mIsPlaying) {
                        mBinder.playPause();
                    } else {
                        if (firstStart) {
                            firstStart = false;
//                            if (isLocal) {
//                                playingLocalSong();
//                            } else {
                            playingSong();
//                            }
                        } else {
                            mBinder.playStart();
                        }
                    }
                }
                break;
            case R.id.iv_next_main:
                playNext();
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
                transaction.setCustomAnimations(R.anim.fragment_playsong_slide_in, R.anim.fragment_songlist_slide_out);
                if (mPlaySongFragment == null) {
                    mPlaySongFragment = new PlaySongFragment();
                }
                mPlaySongFragment.setPlaying(mIsPlaying);
                mPlaySongFragment.setPlaySongBean(mPlaySongBean);
                mPlaySongFragment.setSongListBeen(mMainSongListBeen);
                mPlaySongFragment.setPlayMode(playMode);
                mPlaySongFragment.setPlayingSongListener(mPlayingSongListener);
                transaction.add(R.id.fl_all_main, mPlaySongFragment).commit();
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

        @Override
        public void seekTo(int progress) {
            mBinder.playTo(progress);
        }

        @Override
        public void settingMode(int mode) {
            playMode = mode;
        }
    };

    ShowSongListListener mShowSongListListener = new ShowSongListListener() {
        @Override
        public void showBackListener(Boolean isShow) {
            listShowing = isShow;
        }

        @Override
        public void playItem(int position) {
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
            mSonglistFragment.setSongListListener(mShowSongListListener);
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

    public void updatePlaySongInfo() {
        if (mPlaySongBean.getSonginfo().getPic_big() == null) {
            mTv_songtitle.setText(mMainSongListBeen.get(item).getTitle());
            mTv_author.setText(mMainSongListBeen.get(item).getAuthor());
            mIv_main.setImageResource(R.mipmap.default_skin_thumbnail);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.default_skin_thumbnail);
            mBinder.updateNotificationImg(bitmap);
        } else {
            mTv_songtitle.setText(mPlaySongBean.getSonginfo().getTitle());
            mTv_author.setText(mPlaySongBean.getSonginfo().getAuthor());
            ImageLoader.getInstance().loadImage(
                    mPlaySongBean.getSonginfo().getPic_big(),
                    mOptions,
                    new SimpleImageLoadingListener() {

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            super.onLoadingFailed(imageUri, view, failReason);
                            mIv_main.setImageResource(R.mipmap.default_skin_thumbnail);
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.default_skin_thumbnail);
                            mBinder.updateNotificationImg(bitmap);
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            super.onLoadingComplete(imageUri, view, loadedImage);
                            if (loadedImage != null) {
                                mIv_main.setImageBitmap(loadedImage);
                                mBinder.updateNotificationImg(loadedImage);
                            } else {
                                mIv_main.setImageResource(R.mipmap.default_skin_thumbnail);
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.default_skin_thumbnail);
                                mBinder.updateNotificationImg(bitmap);
                            }

                        }
                    });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getplayNext(Integer i) {
        if (i == StringVlaues.PLAY_NEXT) {
            playNext();
        }
        if (i == StringVlaues.PLAY_PREV) {
            playPrev();
        }
    }

    public void playNext() {
        mBinder.playPause();
        updateItem();
        playingSong();
    }

    public void playPrev() {
        mBinder.playPause();
        updateItem();
        playingSong();
    }

    public void updateItem() {
        if (playMode == StringVlaues.PLAY_MODE_LOOP) {
            if (item >= mMainSongListBeen.size() - 1) {
                item = 0;
            } else if (0 == item) {
                item = mMainSongListBeen.size() - 1;
            } else {
                item++;
            }
        } else if (playMode == StringVlaues.PLAY_MODE_ORDER) {
            if (item >= mMainSongListBeen.size() - 1 || 0 == item) {

            } else {
                item++;
            }
        } else if (playMode == StringVlaues.PLAY_MODE_RANDOM) {
            item = mRandom.nextInt(mMainSongListBeen.size());
        } else if (playMode == StringVlaues.PLAY_MODE_SINGLE_LOOP) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDeleteSongListEvent(PlaySongListEvent playSongListEvent) {
        item = playSongListEvent.getItem();
        mMainSongListBeen.clear();
        mMainSongListBeen.addAll(playSongListEvent.getSongListBeen());
        if (!playSongListEvent.getDelete()) {
            playingSong();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongMenuEvent(PlaySongMenuEvent playSongMenuEvent) {
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
            mIsPlaying = true;
        } else {
            mIv_play.setImageResource(R.mipmap.bt_minibar_play_normal);
            mIsPlaying = false;
        }
    }

//    public void playingLocalSong() {
//        mBinder.playLocal(mMainSongListBeen.get(item).getSong_id());
//        updatePlaySongInfo();
//    }

    public void playingSong() {
        if (!mMainSongListBeen.get(item).getLocal()) {
            PlaySongGsonRequest<PlaySongBean> playSongGsonRequest =
                    new PlaySongGsonRequest<>(
                            URLVlaues.getPlaySong(mMainSongListBeen.get(item).getSong_id()),
                            PlaySongBean.class,
                            new Response.Listener<PlaySongBean>() {
                                @Override
                                public void onResponse(PlaySongBean response) {
                                    if (response != null && response.getBitrate() != null
                                            && response.getBitrate().getFile_link() != null) {
                                        mBinder.play(response);
                                        mPlaySongBean = response;
                                        updatePlaySongInfo();
                                        EventBus.getDefault().post(response);
                                    } else {
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
        } else {
            mPlaySongBean.getSonginfo().setTitle(mMainSongListBeen.get(item).getTitle());
            mPlaySongBean.getSonginfo().setAuthor(mMainSongListBeen.get(item).getAuthor());
            mPlaySongBean.getSonginfo().setPic_big(null);
            mPlaySongBean.getSonginfo().setSong_id(null);
            mBinder.playLocal(mMainSongListBeen.get(item));
            mBinder.updateNotificationInfo(mPlaySongBean);
            updatePlaySongInfo();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        unbindService(mConnection);
        DBtool.getmDBtools().deleteSongList();
        DBtool.getmDBtools().insertSongList(mMainSongListBeen);
        Log.d("Sysout", "mMainSongListBeen.size():" + mMainSongListBeen.size());
        mEditor.putInt(StringVlaues.playItem, item);
        mEditor.putInt(StringVlaues.playMode, playMode);
        mEditor.putBoolean("firststart", true);
//        mEditor.putBoolean("isLocal", isLocal);
        mEditor.commit();
        super.onDestroy();
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
