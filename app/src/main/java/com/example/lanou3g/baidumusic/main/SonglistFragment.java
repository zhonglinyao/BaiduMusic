package com.example.lanou3g.baidumusic.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.lanou3g.baidumusic.MyApp;
import com.example.lanou3g.baidumusic.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public class SonglistFragment extends BaseFragment {

    private LinearLayout mLl_back;
    private ListView mLv;
    private ShowSongListListener mSongListListener;
    private List<MainSongListBean> mSongListBeen;
    private SongListFragmentAdapter mAdapter;
    private PlaySongBean mPlaySongBean;

    public void setPlaySongBean(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
    }

    public void setSongListBeen(List<MainSongListBean> songListBeen) {
        mSongListBeen = songListBeen;
    }

    public void setSongListListener(ShowSongListListener songListListener) {
        mSongListListener = songListListener;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_songlist;
    }

    @Override
    protected void initView() {
        mLl_back = bindView(R.id.ll_back);
        mLl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.anim_null, R.anim.fragment_songlist_slide_out);
                transaction.remove(SonglistFragment.this);
                transaction.commit();
            }
        });
        mLv = bindView(R.id.lv_songlist_main);
        View bottomView = LayoutInflater.from(context).inflate(R.layout.layout_footerview_null, null);
        View v = bottomView.findViewById(R.id.footerView);
        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.height = MyApp.getWindowHeight() / 13;
        v.setLayoutParams(params);
        mLv.addFooterView(bottomView);
    }

    @Override
    protected void initData() {
        mAdapter = new SongListFragmentAdapter(context);
        mAdapter.setSongListBeen(mSongListBeen);
        mAdapter.setPlaySongBean(mPlaySongBean);
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSongListListener.playItem(position);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSongListListener.showBackListener(false);
        EventBus.getDefault().unregister(mAdapter);
    }
}
