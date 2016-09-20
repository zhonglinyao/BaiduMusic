package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.GsonRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.URLVlaues;
import com.example.lanou3g.baidumusic.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment {

    private ViewPager vp;
    private LinearLayout ll;
    private ImageHander hander = new ImageHander(new WeakReference<RecommendFragment>(this));
    private ArrayList<ImageView> imageViews = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.fragment_recomment;
    }

    @Override
    protected void initView() {
        View view = bindView(R.id.ic_recomment);
        vp = bindView(R.id.vp_carouse, view);
        ll = bindView(R.id.ll_carouse, view);
    }

    @Override
    protected void initData() {
        final CarouseAdapter adapter = new CarouseAdapter();
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hander.sendMessage(Message.obtain(hander, ImageHander.MSG_PAGE, position, 0));
                int j = position % imageViews.size();
                for (int i = 0; i < imageViews.size(); i++) {
                    if (i == j) {
                        imageViews.get(i).setBackgroundResource(R.mipmap.ic_dot_default_selected);
                    } else {
                        imageViews.get(i).setBackgroundResource(R.mipmap.ic_dot_default_unselected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        hander.sendEmptyMessage(ImageHander.MSG_KEEP);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        hander.sendEmptyMessageDelayed(ImageHander.MSG_UPDATE, ImageHander.MSG_DELAY);
                        break;
                }
            }
        });
//        final ArrayList<ImageView> views = new ArrayList<>();
        final ArrayList<SimpleDraweeView> views = new ArrayList<>();
        GsonRequest<RecommendCarouseBean> gsonRequest =
                new GsonRequest<RecommendCarouseBean>(URLVlaues.RECOMMEND_CAROUSE,
                        RecommendCarouseBean.class, new Response.Listener<RecommendCarouseBean>() {
                    @Override
                    public void onResponse(RecommendCarouseBean response) {
                        Fresco.initialize(context);
                        List<RecommendCarouseBean.PicBean> list = response.getPic();
                        for (int i = 0; i < list.size(); i++) {
                            ImageView imageView = new ImageView(context);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 10);
                            params.setMargins(5, 0, 5, 0);
                            imageView.setLayoutParams(params);
                            if (i == 0) {
                                imageView.setBackgroundResource(R.mipmap.ic_dot_default_selected);
                            } else {
                                imageView.setBackgroundResource(R.mipmap.ic_dot_default_unselected);
                            }
                            imageViews.add(imageView);
                            ll.addView(imageViews.get(i));
                        }

                        for (int i = 0; i < list.size(); i++) {
                            SimpleDraweeView view = new SimpleDraweeView(context);
                            Uri uri = Uri.parse(list.get(i).getRandpic());
                            view.setImageURI(uri);
                            view.setScaleType(ImageView.ScaleType.MATRIX);
//                            Picasso.with(context).load(list.get(i).getRandpic()).into(view);
                            views.add(view);
                        }
                        adapter.setViews(views);
                        hander.sendEmptyMessageDelayed(ImageHander.MSG_UPDATE, ImageHander.MSG_DELAY);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "无法连接到网络", Toast.LENGTH_SHORT).show();
                    }
                });

        VolleyRequestQueue.getVolleyRequestQueue().addRequest(gsonRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ll.removeAllViews();
        imageViews.clear();
    }


    private static class ImageHander extends Handler {
        protected static final int MSG_UPDATE = 1;
        protected static final int MSG_KEEP = 2;
        protected static final int MSG_BREAK = 3;
        protected static final int MSG_PAGE = 4;
        protected static final int MSG_DELAY = 2000;
        private int courrentItem = 0;
        private WeakReference<RecommendFragment> weakReference;

        public ImageHander(WeakReference<RecommendFragment> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecommendFragment fragment = weakReference.get();
            if (fragment == null) {
                return;
            }
            if (fragment.hander.hasMessages(MSG_UPDATE)) {
                fragment.hander.removeMessages(MSG_UPDATE);
            }
            switch (msg.what) {
                case MSG_UPDATE:
                    courrentItem++;
                    fragment.vp.setCurrentItem(courrentItem);
                    fragment.hander.sendEmptyMessageDelayed(MSG_UPDATE, MSG_DELAY);
                    break;
                case MSG_KEEP:
                    break;
                case MSG_BREAK:
                    fragment.hander.sendEmptyMessageDelayed(MSG_UPDATE, MSG_DELAY);
                    break;
                case MSG_PAGE:
                    courrentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }
}
