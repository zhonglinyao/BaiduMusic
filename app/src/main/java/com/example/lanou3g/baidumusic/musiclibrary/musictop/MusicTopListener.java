package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import com.example.lanou3g.baidumusic.bean.MusicTopBean;

/**
 * Created by dllo on 16/10/8.
 */
public interface MusicTopListener {
    void playAllListener(MusicTopBean.TopNameBean topNameBean);
    void jumpListener(MusicTopBean.TopNameBean topNameBean);
}
