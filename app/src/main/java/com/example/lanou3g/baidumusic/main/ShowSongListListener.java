package com.example.lanou3g.baidumusic.main;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public interface ShowSongListListener {
    void showBackListener(Boolean isShow);
    void playItem(List<MainSongListBean> mainSongListBeen, int position);
}
