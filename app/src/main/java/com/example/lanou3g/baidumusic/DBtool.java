package com.example.lanou3g.baidumusic;

import android.os.Handler;
import android.os.Looper;

import com.example.lanou3g.baidumusic.main.MainSongListBean;
import com.example.lanou3g.baidumusic.main.PlaySongListEvent;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/11.
 */
public class DBtool {
    private static DBtool mDBtools;
    private LiteOrm mLiteOrm;
    private Handler mHandler;


    public static DBtool getmDBtools() {
        if (mDBtools == null){
            synchronized (DBtool.class){
                if (mDBtools == null){
                    mDBtools = new DBtool();
                }
            }
        }
        return mDBtools;
    }

    private DBtool() {
        mLiteOrm = LiteOrm.newCascadeInstance(MyApp.getmContext(), "lanou.db");
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void insertPlaySongEvent(final PlaySongListEvent playSongListEvent){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(playSongListEvent);
            }
        });
    }

    public void deletePlaySongEvent(){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(PlaySongListEvent.class);
            }
        });
    }

    public void updatePlaySongEvent(final PlaySongListEvent playSongListEvent){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.update(playSongListEvent);
            }
        });
    }

    public void querySongList(final QueryListener<MainSongListBean> queryListener){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<MainSongListBean> songListBeen = mLiteOrm.query(MainSongListBean.class);
                mHandler.post(new HandlerRunnable<MainSongListBean>(songListBeen, queryListener));
            }
        });
    }

    public void queryPlaySongEvent(final QueryListener<PlaySongListEvent> queryListener){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<PlaySongListEvent> playSongListEvents = mLiteOrm.query(PlaySongListEvent.class);
                mHandler.post(new HandlerRunnable<>(playSongListEvents, queryListener));
            }
        });
    }

    class HandlerRunnable<T> implements Runnable{
        private List<T> mTList;
        private QueryListener<T> mTQueryListener;

        public HandlerRunnable(List<T> TList, QueryListener<T> TQueryListener) {
            mTList = TList;
            mTQueryListener = TQueryListener;
        }

        @Override
        public void run() {
            mTQueryListener.onQuert(mTList);
        }
    }

    public interface QueryListener<T>{
        void onQuert(List<T> list);
    }
}
