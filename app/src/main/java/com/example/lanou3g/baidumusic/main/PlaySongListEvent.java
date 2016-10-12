package com.example.lanou3g.baidumusic.main;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.util.List;

/**
 * Created by dllo on 16/10/12.
 */
public class PlaySongListEvent {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private int item;
    private List<MainSongListBean> mSongListBeen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
