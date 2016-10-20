package com.example.lanou3g.baidumusic.mine.login;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/10/14.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mIv_qq;
    private ImageView mIv_wechat;
    private ImageView mIv_sina;
    private ImageView mIv_back;

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        mIv_qq = bindView(R.id.iv_qq_login_mine);
        mIv_wechat = bindView(R.id.iv_wechat_login_mine);
        mIv_sina = bindView(R.id.iv_sina_login_mine);
        mIv_back = bindView(R.id.iv_back_login_mine);

    }

    @Override
    protected void initData() {
        mIv_qq.setOnClickListener(this);
        mIv_wechat.setOnClickListener(this);
        mIv_sina.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_qq_login_mine:
                ShareSDK.initSDK(context);
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
//                qq.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;
            case R.id.iv_wechat_login_mine:
                break;
            case R.id.iv_sina_login_mine:
                ShareSDK.initSDK(context);
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
//                weibo.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;
            case R.id.iv_back_login_mine:
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                transaction.remove(LoginFragment.this);
                transaction.commit();
                break;
            default:
                break;
        }
    }
}
