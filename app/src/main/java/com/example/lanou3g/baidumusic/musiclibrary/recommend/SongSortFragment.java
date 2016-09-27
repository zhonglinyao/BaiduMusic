package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseFragment;

/**
 * Created by dllo on 16/9/26.
 */
public class SongSortFragment extends BaseFragment{
    @Override
    protected int setLayout() {
        return R.layout.fragment_song_sort;
    }

    @Override
    protected void initView() {
        ImageButton ib_back = bindView(R.id.ib_back_song_sort);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                transaction.remove(SongSortFragment.this);
                transaction.commit();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
