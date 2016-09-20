package com.example.lanou3g.baidumusic.welcome;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.example.lanou3g.baidumusic.main.MainActivity;

/**
 * Created by dllo on 16/9/20.
 */
public class WelcomeSecondFragment extends BaseFragment{

    private Button button;

    @Override
    protected int setLayout() {
        return R.layout.fragment_welcome_second;
    }

    @Override
    protected void initView() {
        button = bindView(R.id.btn_welcome);
    }

    @Override
    protected void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}
