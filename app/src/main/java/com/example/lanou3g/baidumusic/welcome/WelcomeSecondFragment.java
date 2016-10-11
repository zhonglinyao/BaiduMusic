package com.example.lanou3g.baidumusic.welcome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.Tools;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.main.MainActivity;

/**
 * Created by dllo on 16/9/20.
 */
public class WelcomeSecondFragment extends BaseFragment{

    private TextView mTextView;
    private RelativeLayout mRl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_welcome_second;
    }

    @Override
    protected void initView() {
        mTextView = bindView(R.id.tv_welcome);
        mRl = bindView(R.id.rl_welcome_second);
    }

    @Override
    protected void initData() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.welcome_5820_2, options);
        options.inSampleSize = Tools.calculateInSampleSize(options, MyApp.getWindowWidth(), MyApp.getWindowHeight());
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.welcome_5820_2, options);
        mRl.setBackground(new BitmapDrawable(getResources(), bitmap));
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}
