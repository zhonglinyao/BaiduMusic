package com.example.lanou3g.baidumusic.mine;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.DownloadSongBean;
import com.example.lanou3g.baidumusic.bean.LocalMusicBean;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.mine.download.DownloadFragment;
import com.example.lanou3g.baidumusic.mine.local.LocalMusicFragment;
import com.example.lanou3g.baidumusic.mine.login.LoginFragment;
import com.example.lanou3g.baidumusic.tools.DBtool;
import com.example.lanou3g.baidumusic.tools.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout mRl_login;
    private TextView mTv_downLoad_count;
    private RelativeLayout mRl_download_mine;
    private ArrayList<DownloadSongBean> mDownloadSongBeen = new ArrayList<>();
    private LoginFragment mLoginFragment;
    private DownloadFragment mDownloadFragment;
    private ArrayList<LocalMusicBean> mLocalMusicBeen = new ArrayList<>();
    private TextView mTv_localMusic;
    private RelativeLayout mRl_local;
    private LocalMusicFragment mLocalMusicFragment;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        mRl_login = bindView(R.id.rl_login_mine);
        mTv_downLoad_count = bindView(R.id.tv_downLoad_count);
        mRl_download_mine = bindView(R.id.rl_download_mine);
        mTv_localMusic = bindView(R.id.tv_local_song_count);
        mRl_local = bindView(R.id.rl_local_mine);
    }

    @Override
    protected void initData() {
        updateDownloadCount();
        mRl_login.setOnClickListener(this);
        mRl_download_mine.setOnClickListener(this);
        mRl_local.setOnClickListener(this);
        mLocalMusicBeen.addAll(Tools.localMusicLoader(context.getContentResolver()));
        mTv_localMusic.setText(mLocalMusicBeen.size() + "首");
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
        switch (v.getId()) {
            case R.id.rl_login_mine:
                if (mLoginFragment == null) {
                    mLoginFragment = new LoginFragment();
                }
                transaction.add(R.id.fl_all_main, mLoginFragment);
                break;
            case R.id.rl_download_mine:
                if (mDownloadFragment == null) {
                    mDownloadFragment = new DownloadFragment();
                }
                mDownloadFragment.setDownloadSongBeen(mDownloadSongBeen);
                transaction.add(R.id.fl_all_main, mDownloadFragment);
                break;
            case R.id.rl_local_mine:
                if (mLocalMusicFragment == null){
                    mLocalMusicFragment = new LocalMusicFragment();
                }
                mLocalMusicFragment.setLocalMusicBeen(mLocalMusicBeen);
                transaction.add(R.id.fl_all_main, mLocalMusicFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    public void updateDownloadCount() {
        DBtool.getmDBtools().queryDownloadSong(new DBtool.QueryListener<DownloadSongBean>() {
            @Override
            public void onQuert(List<DownloadSongBean> list) {
                if (list.size() == 0 || list == null) {
                    mTv_downLoad_count.setText("0首");
                } else {
                    mDownloadSongBeen.clear();
                    mDownloadSongBeen.addAll(list);
                    mTv_downLoad_count.setText(mDownloadSongBeen.size() + "首");
                }
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            updateDownloadCount();
            mLocalMusicBeen.clear();
            mLocalMusicBeen.addAll(Tools.localMusicLoader(context.getContentResolver()));
            mTv_localMusic.setText(mLocalMusicBeen.size() + "首");
        }
    }


}
