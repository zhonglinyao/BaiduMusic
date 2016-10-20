package com.example.lanou3g.baidumusic.mine.download;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.DownloadSongBean;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/15.
 */
public class DownloadFragment extends BaseFragment {

    private ImageView mIv_back;
    private ListView mLv;
    private ArrayList<DownloadSongBean> mDownloadSongBeen;
    private DownloadAdapter mAdapter;
    private TextView mTv_toolbar;

    public void setDownloadSongBeen(ArrayList<DownloadSongBean> downloadSongBeen) {
        mDownloadSongBeen = downloadSongBeen;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine_item;
    }

    @Override
    protected void initView() {
        mIv_back = bindView(R.id.iv_back_download_mine);
        mLv = bindView(R.id.lv_download_mine);
        mTv_toolbar = bindView(R.id.tv_toolbar);
    }

    @Override
    protected void initData() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                transaction.remove(DownloadFragment.this);
                transaction.commit();
            }
        });

        mTv_toolbar.setText("我的下载");
        if (mDownloadSongBeen.size() >= 0) {
            mAdapter = new DownloadAdapter(context);
            mAdapter.setDownloadSongBeen(mDownloadSongBeen);
            mLv.setAdapter(mAdapter);
        }

    }
}
