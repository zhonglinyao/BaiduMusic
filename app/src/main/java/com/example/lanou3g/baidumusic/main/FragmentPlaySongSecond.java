package com.example.lanou3g.baidumusic.main;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/10/10.
 */
public class FragmentPlaySongSecond extends BaseFragment {

    private ImageView mIv_src;
    private ImageView mIv_background;
    private TextView mTv_title;
    private TextView mTv_author;
    private LinearLayout mLl;
    private PlaySongBean mPlaySongBean;

    public void setPlaySongBean(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
    }

    @Override
    protected int setLayout() {
        return R.layout.layout_playsong_second;
    }

    public ImageView getIv_src() {
        return mIv_src;
    }

    @Override
    protected void initView() {
        mIv_src = bindView(R.id.iv_playsong_second_src);
        mIv_background = bindView(R.id.iv_playsong_second_background);
        mTv_title = bindView(R.id.tv_playsong_second_title);
        mTv_author = bindView(R.id.tv_playsong_second_author);
        mLl = bindView(R.id.ll_playsong_second_singer);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mIv_background.setBackgroundColor(Color.BLACK);
        update();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongInfo(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
        update();
    }

    public void update() {
        if (mPlaySongBean != null && mPlaySongBean.getSonginfo() != null) {
            if (mPlaySongBean.getSonginfo().getPic_huge() != null) {
                ImageLoader.getInstance().loadImage(mPlaySongBean.getSonginfo().getPic_huge(), ImageLoderSetting.getOptions(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        super.onLoadingCancelled(imageUri, view);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        mIv_src.setImageBitmap(loadedImage);
                        Bitmap bitmap = Tools.changeBackgroundImage(loadedImage, 24.0f);
                        mIv_background.setBackground(new BitmapDrawable(getResources(), bitmap));
                    }
                });
            }
            mTv_title.setText(mPlaySongBean.getSonginfo().getTitle());
            mTv_author.setText(mPlaySongBean.getSonginfo().getAuthor());
        } else {
            mIv_src.setImageResource(R.mipmap.default_album_playing);
            mIv_background.setBackgroundColor(Color.BLACK);
            mTv_title.setText("");
            mTv_author.setText("");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
