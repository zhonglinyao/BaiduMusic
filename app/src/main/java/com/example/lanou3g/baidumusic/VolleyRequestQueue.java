package com.example.lanou3g.baidumusic;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/9/20.
 */
public class VolleyRequestQueue extends Volley{
    private static VolleyRequestQueue volleyRequestQueue;
    private static RequestQueue queue;

    public static VolleyRequestQueue getVolleyRequestQueue() {
        if (volleyRequestQueue == null){
            synchronized (VolleyRequestQueue.class){
                if (volleyRequestQueue == null){
                    volleyRequestQueue = new VolleyRequestQueue();
                }
            }
        }
        return volleyRequestQueue;
    }

    private  VolleyRequestQueue() {
        queue = Volley.newRequestQueue(MyApp.getmContext());
    }

    public void addRequest(Request request){
        queue.add(request);
    }
}
