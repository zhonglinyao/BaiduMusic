package com.example.lanou3g.baidumusic.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Created by dllo on 16/10/8.
 */
public class MainSongListBean implements Serializable{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String title;
    private String author;
    private String song_id;
    private Boolean isLocal = false;

    public Boolean getLocal() {
        return isLocal;
    }

    public void setLocal(Boolean local) {
        isLocal = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MainSongListBean{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", song_id='" + song_id + '\'' +
                ", id=" + id +
                '}';
    }
}
