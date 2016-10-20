package com.example.lanou3g.baidumusic.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/12.
 */
public class PlaySongListEvent {
    private int item;
    private List<MainSongListBean> mSongListBeen;
    private Boolean isDelete = false;

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

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
