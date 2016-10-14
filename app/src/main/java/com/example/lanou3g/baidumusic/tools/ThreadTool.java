package com.example.lanou3g.baidumusic.tools;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dllo on 16/10/11.
 */
public class ThreadTool {
    private static ThreadTool mThreadTool;
    private ThreadPoolExecutor mThreadPoolExecutor;

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return mThreadPoolExecutor;
    }

    public static ThreadTool getInstance() {
        if (mThreadTool == null){
            synchronized (ThreadTool.class){
                if (mThreadTool == null){
                    mThreadTool = new ThreadTool();
                }
            }
        }
        return mThreadTool;
    }

    private ThreadTool() {
        int CUPCore = Runtime.getRuntime().availableProcessors();
        mThreadPoolExecutor = new ThreadPoolExecutor(CUPCore + 1,
                CUPCore * 2 + 1, 60l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
