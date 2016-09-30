package com.example.lanou3g.baidumusic.main;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 16/9/30.
 */
public class PlaySongGsonRequest<T>  extends Request<T> {
    private  Response.Listener<T> mListener;
    private Class<T> mClass;

    public PlaySongGsonRequest(int method,
                               String url,
                               Class<T> clazz,
                               Response.Listener<T> listener,
                               Response.ErrorListener errorLlistener) {
        super(method, url, errorLlistener);
        mClass = clazz;
        mListener = listener;
    }

    public PlaySongGsonRequest(String url,
                               Class<T> clazz,
                               Response.Listener<T> listener,
                               Response.ErrorListener errorLlistener) {
        this(Method.GET, url, clazz, listener, errorLlistener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        parsed = parsed.substring(1, parsed.length() - 2);
        Gson gson = new Gson();
        return Response.success(gson.fromJson(parsed, mClass), HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
