package com.example.lanou3g.baidumusic.main;

import com.example.lanou3g.baidumusic.musiclibrary.songmenu.HotSongMenuBean;

import java.util.List;

/**
 * Created by dllo on 16/10/7.
 */
public class PlaySongMenuEvent {
    private int item;
    private List<HotSongMenuBean.ContentBean> mContentBeen;

    public List<HotSongMenuBean.ContentBean> getContentBeen() {
        return mContentBeen;
    }

    public void setContentBeen(List<HotSongMenuBean.ContentBean> contentBeen) {
        mContentBeen = contentBeen;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

}
