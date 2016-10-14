package com.example.lanou3g.baidumusic.tools;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.bean.MainSongListBean;
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

    public void insertSongList(final List<MainSongListBean> songListBeen){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.insert(songListBeen);
            }
        });
    }

    public void deleteSongList(){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.deleteAll(MainSongListBean.class);
            }
        });
    }

    public void querySongList(final QueryListener<MainSongListBean> queryListener){
        ThreadTool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<MainSongListBean> songListBeen = mLiteOrm.query(MainSongListBean.class);
                Log.d("Sysout", "songListBeen.size():" + songListBeen.size());
                mHandler.post(new HandlerRunnable<MainSongListBean>(songListBeen, queryListener));
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
