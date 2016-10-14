package com.example.lanou3g.baidumusic.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseActivity;
import com.example.lanou3g.baidumusic.main.MainActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class WelcomeActivity extends BaseActivity {

    private ViewPager vp;

    @Override
    protected int setLayout() {
        SharedPreferences preferences = getSharedPreferences("welcome", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (preferences.getBoolean("is", false)) {
            editor.commit();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            editor.putBoolean("is", true);
            editor.commit();
        }
        return R.layout.activity_welcome;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void initView() {
        vp = bindView(R.id.vp_welcome);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new WelcomeFirstFragment());
        fragments.add(new WelcomeSecondFragment());
        WelcomeAdapter adapter = new WelcomeAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
//        PagerAdapter adapter = new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 2;
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                ImageView imageView = new ImageView(WelcomeActivity.this);
//                imageView.setImageResource(R.mipmap.ic_aboutus_logo);
//                container.addView(imageView);
//                return imageView;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((View) object);
//            }
//        };
        vp.setAdapter(adapter);

    }
}
