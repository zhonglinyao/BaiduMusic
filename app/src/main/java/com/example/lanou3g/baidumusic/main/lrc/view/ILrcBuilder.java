package com.example.lanou3g.baidumusic.main.lrc.view;

import com.example.lanou3g.baidumusic.main.lrc.view.impl.LrcRow;

import java.util.List;

/**
 * 解析歌词，得到LrcRow的集合
 */
public interface ILrcBuilder {
    List<LrcRow> getLrcRows(String rawLrc);
}
