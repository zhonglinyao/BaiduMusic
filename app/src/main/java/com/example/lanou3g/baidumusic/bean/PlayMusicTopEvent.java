package com.example.lanou3g.baidumusic.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public class PlayMusicTopEvent {
    private int item;
    private List<MusicTopSongListBean.SongListBean> mSongListBeen;

    public List<MusicTopSongListBean.SongListBean> getSongListBeen() {
        return mSongListBeen;
    }

    public void setSongListBeen(List<MusicTopSongListBean.SongListBean> songListBeen) {
        mSongListBeen = songListBeen;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }


}
