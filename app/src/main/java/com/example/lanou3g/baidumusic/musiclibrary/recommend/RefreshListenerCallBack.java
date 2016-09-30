package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.widget.TextView;

/**
 * Created by dllo on 16/9/29.
 */
public interface RefreshListenerCallBack {
    void refresh(TextView v, int pageNum, int haveMore, boolean isNow);
}
