package com.example.lanou3g.baidumusic.bean;

/**
 * Created by dllo on 16/10/14.
 */
public class LocalMp3Bean {
    private long id;
    private long album_id;
    private String title;
    private String artist;
    private long duration;
    private long size;
    private String url;
    private String album;
    private int isMusic;
    private boolean isFavorite = false;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){return this.id;}

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){return this.title;}

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getArtist(){return this.artist;}

    public void setDuration(long duration){this.duration = duration;}

    public long getDuration(){return this.duration;}

    public void setSize(long size){this.size = size;}

    public long getSize(){return this.size;}

    public void setUrl(String url){this.url = url;}

    public String getUrl(){return this.url;}

    public void setAlbum(String album){this.album = album;}

    public String getAlbum(){return this.album;}

    public void setAlbum_id(long album_id){this.album_id = album_id;}

    public long getAlbum_id(){return this.album_id;}

    public void setFavorite(boolean favorite){this.isFavorite =favorite;}

    public boolean getFavorite(){return isFavorite;}

}
