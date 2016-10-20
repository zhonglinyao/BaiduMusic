package com.example.lanou3g.baidumusic.bean;

/**
 * Created by dllo on 16/10/14.
 */
public class LocalMusicBean implements Comparable<LocalMusicBean>{

    private long id;
    private String title;
    private String album;
    private int duration;
    private long size;
    private String artist;
    private String url;

    public LocalMusicBean() {
    }

    public LocalMusicBean(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(LocalMusicBean another) {
        return this.getTitle().compareTo(another.getTitle());
    }
}
