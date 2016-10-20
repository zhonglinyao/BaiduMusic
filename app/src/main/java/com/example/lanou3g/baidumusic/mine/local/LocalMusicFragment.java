package com.example.lanou3g.baidumusic.mine.local;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.LocalMusicBean;
import com.example.lanou3g.baidumusic.bean.MainSongListBean;
import com.example.lanou3g.baidumusic.bean.PlaySongListEvent;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/15.
 */
public class LocalMusicFragment extends BaseFragment {
    private ImageView mIv_back;
    private ListView mLv;
    private TextView mTv_toolbar;
    private ArrayList<LocalMusicBean> mLocalMusicBeen = new ArrayList<>();
    private LocalMusicAdapter mAdapter;

    public void setLocalMusicBeen(ArrayList<LocalMusicBean> localMusicBeen) {
        mLocalMusicBeen = localMusicBeen;
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
                transaction.remove(LocalMusicFragment.this);
                transaction.commit();
            }
        });
        mTv_toolbar.setText("本地音乐");
        mAdapter = new LocalMusicAdapter(context);
        mLv.setAdapter(mAdapter);
        if (mLocalMusicBeen.size() >= 0) {
            mAdapter.setLocalMusicBeen(mLocalMusicBeen);
        }
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private PlaySongListEvent mPlaySongListEvent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LocalMusicFragment", "aa");
                ArrayList<MainSongListBean> mainSongListBeen = new ArrayList<>();
                for (int i = 0; i < mLocalMusicBeen.size(); i++) {
                    MainSongListBean mainSongListBean = new MainSongListBean();
                    mainSongListBean.setTitle(mLocalMusicBeen.get(i).getTitle());
                    mainSongListBean.setAuthor(mLocalMusicBeen.get(i).getArtist());
                    mainSongListBean.setSong_id(mLocalMusicBeen.get(i).getUrl());
                    mainSongListBean.setLocal(true);
                    mainSongListBeen.add(mainSongListBean);
                }
                if (mPlaySongListEvent == null) {
                    mPlaySongListEvent = new PlaySongListEvent();
                }
                mPlaySongListEvent.setDelete(false);
                mPlaySongListEvent.setItem(position);
                mPlaySongListEvent.setSongListBeen(mainSongListBeen);
                EventBus.getDefault().post(mPlaySongListEvent);
            }
        });
    }
}
