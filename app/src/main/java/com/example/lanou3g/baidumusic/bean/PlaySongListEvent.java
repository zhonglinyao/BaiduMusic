package com.example.lanou3g.baidumusic.bean;

import com.example.lanou3g.baidumusic.bean.MainSongListBean;

import java.util.List;

/**
 * Created by dllo on 16/10/12.
 */
public class PlaySongListEvent {
    private int item;
    private List<MainSongListBean> mSongListBeen;

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public List<MainSongListBean> getSongListBeen() {
        return mSongListBeen;
    }

    public void setSongListBeen(List<MainSongListBean> songListBeen) {
        mSongListBeen = songListBeen;
    }
}
