package com.example.lanou3g.baidumusic.main.playsong;

/**
 * Created by dllo on 16/10/11.
 */
public interface PlayingSongListener {
    void isPlay(Boolean isPlaying);
    void playingNext();
    void playingPrev();
    void seekTo(int progress);
    void settingMode(int mode);
}
