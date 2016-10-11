package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.DividerItemDecoration;
import com.example.lanou3g.baidumusic.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.main.ImageLoderSetting;
import com.example.lanou3g.baidumusic.main.PlayMusicTopEvent;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dllo on 16/10/8.
 */
public class MusicTopSongListFragment extends BaseFragment {

    private RecyclerView mRv;
    private ImageView mIv_back;
    private ImageView mImg;
    private TextView mTv_date;
    private AppBarLayout mAbl;
    private MusicTopSongListBean mMusicTopSongListBean;
    private TextView mTv_count;
    private TextView mTv_title;
    private MusicTopSongListAdapter mAdapter;
    private TextView mTv_playAll;
    private PlayMusicTopEvent mPlayMusicTopEvent;

    public void setMusicTopSongListBean(MusicTopSongListBean musicTopSongListBean) {
        mMusicTopSongListBean = musicTopSongListBean;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_musictop_songlist;
    }

    @Override
    protected void initView() {
        mRv = bindView(R.id.rv_musictop_songlist);
        mIv_back = bindView(R.id.iv_back_musictop_songlist);
        mImg = bindView(R.id.iv_musictop_songlist);
        mTv_date = bindView(R.id.tv_date_musictop_songlist);
        mAbl = bindView(R.id.abl_musictop_songlist);
        mTv_count = bindView(R.id.tv_count_musictop_songlist);
        mTv_title = bindView(R.id.tv_title_musictop_songlist);
        mTv_playAll = bindView(R.id.tv_playall_musictop_songlist);

    }

    @Override
    protected void initData() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                transaction.remove(MusicTopSongListFragment.this);
                transaction.commit();
            }
        });

        ViewGroup.LayoutParams params = mImg.getLayoutParams();
        params.width = MyApp.getWindowWidth();
        params.height = MyApp.getWindowHeight() / 4;
        mImg.setLayoutParams(params);
        ImageLoader.getInstance().loadImage(mMusicTopSongListBean.getBillboard().getPic_s640(),
                ImageLoderSetting.getMusicTopSongListOptions(),
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        super.onLoadingCancelled(imageUri, view);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        Drawable drawable = new BitmapDrawable(getResources(), loadedImage);
                        mAbl.setBackground(drawable);
                    }
                });
        mTv_date.setText("更新日期: " + mMusicTopSongListBean.getBillboard().getUpdate_date());
        mTv_count.setText("/" + mMusicTopSongListBean.getSong_list().size() + "首歌");
        mTv_title.setText(mMusicTopSongListBean.getBillboard().getName());
        mAdapter = new MusicTopSongListAdapter(context);
        mAdapter.setSongListBeen(mMusicTopSongListBean.getSong_list());
        LinearLayoutManager manager = new LinearLayoutManager(context);
        mRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mAdapter);
        mAbl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < -(MyApp.getWindowHeight() / 5.2)) {
                    mTv_title.setVisibility(View.VISIBLE);
                    mTv_date.setVisibility(View.INVISIBLE);
                }
                if (verticalOffset > -(MyApp.getWindowHeight() / 5.2)) {
                    mTv_title.setVisibility(View.INVISIBLE);
                    mTv_date.setVisibility(View.VISIBLE);
                }
            }
        });

        mTv_playAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mPlayMusicTopEvent == null){
                    mPlayMusicTopEvent = new PlayMusicTopEvent();
                }
                mPlayMusicTopEvent.setItem(0);
                mPlayMusicTopEvent.setSongListBeen(mMusicTopSongListBean.getSong_list());
                EventBus.getDefault().post(mPlayMusicTopEvent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(mAdapter);
    }
}
