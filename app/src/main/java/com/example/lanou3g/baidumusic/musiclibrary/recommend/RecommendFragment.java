package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.lanou3g.baidumusic.GsonRequest;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.URLVlaues;
import com.example.lanou3g.baidumusic.VolleyRequestQueue;
import com.example.lanou3g.baidumusic.main.BaseFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class RecommendFragment extends BaseFragment {

    private ImageHander hander = new ImageHander(new WeakReference<RecommendFragment>(this));
    private RecyclerView rv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        rv = bindView(R.id.rv_recommend);
    }

    @Override
    protected void initData() {
        final RecommendAdapter adapter = new RecommendAdapter(context);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);

//        final CarouseAdapter adapter = new CarouseAdapter(context);
//        vp.setAdapter(adapter);
        final GsonRequest<RecommendBean> request = new GsonRequest<RecommendBean>(URLVlaues.RECOMMEND,
                RecommendBean.class,
                new Response.Listener<RecommendBean>() {
                    @Override
                    public void onResponse(RecommendBean response) {
                        List<RecommendBean.ResultBean.FocusBean.FocusResultBean> focusResultBeen = response.getResult().getFocus().getFocusResult();
                        List<RecommendBean.ResultBean.EntryBean.EntryResultBean> entryResultBeen = response.getResult().getEntry().getEntryResult();
                        List<RecommendBean.ResultBean.DiyBean.DiyResultBean> diyResultBeen = response.getResult().getDiy().getDiyResult();
                        List<RecommendBean.ResultBean.Mix1Bean.Mix1ResultBean> mix1ResultBeen = response.getResult().getMix1().getMix1Result();
                        List<RecommendBean.ResultBean.Mix22Bean.Mix22ResultBean> mix22ResultBeen = response.getResult().getMix22().getMix22Result();
                        List<RecommendBean.ResultBean.AdSmallBean.AdSmallResultBean> adSmallResultBeen = response.getResult().getAdSmall().getAdSmallResult();
                        List<RecommendBean.ResultBean.SceneBean.SceneResultBean.ActionBean> actionBeen = response.getResult().getScene().getSceneResult().getAction();
                        List<RecommendBean.ResultBean.RecsongBean.RecsongResultBean> recsongResultBeen = response.getResult().getRecsong().getRecsongResult();
                        List<RecommendBean.ResultBean.Mix9Bean.Mix9ResultBean> mix9ResultBeen = response.getResult().getMix9().getMix9Result();
                        List<RecommendBean.ResultBean.Mix5Bean.Mix5ResultBean> mix5ResultBeen = response.getResult().getMix5().getMix5Result();
                        List<RecommendBean.ResultBean.RadioBean.RadioResultBean> radioResultBeen = response.getResult().getRadio().getRadioResult();
                        List<RecommendBean.ResultBean.Mod7Bean.Mod7ResultBean> mod7ResultBeen = response.getResult().getMod7().getMod7Result();
                        ArrayList<List> lists = new ArrayList<>();
                        lists.add(focusResultBeen);
                        lists.add(entryResultBeen);
                        lists.add(diyResultBeen);
                        lists.add(mix1ResultBeen);
                        lists.add(mix22ResultBeen);
                        lists.add(adSmallResultBeen);
                        lists.add(actionBeen);
                        lists.add(recsongResultBeen);
                        lists.add(mix9ResultBeen);
                        lists.add(mix5ResultBeen);
                        lists.add(radioResultBeen);
                        lists.add(mod7ResultBeen);
                        ArrayList<RecommendBean.ModuleBean> moduleBeen = (ArrayList<RecommendBean.ModuleBean>) response.getModule();
                        moduleBeen.remove(moduleBeen.size() - 1);
                        moduleBeen.remove(4);
                        moduleBeen.remove(2);
                        adapter.setLists(lists);
                        adapter.setModuleBeen(moduleBeen);
                        rv.setAdapter(adapter);
//                for (int i = 0; i < list.size(); i++) {
//                    ImageView imageView = new ImageView(context);
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 10);
//                    params.setMargins(5, 0, 5, 0);
//                    imageView.setLayoutParams(params);
//                    if (i == 0) {
//                        imageView.setBackgroundResource(R.mipmap.ic_dot_default_selected);
//                    } else {
//                        imageView.setBackgroundResource(R.mipmap.ic_dot_default_unselected);
//                    }
//                    imageViews.add(imageView);
//                    ll.addView(imageViews.get(i));
//                }
//                adapter.setFocusResultBeen(list);
//                vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                    }
//
//                    @Override
//                    public void onPageSelected(int position) {
//                        hander.sendMessage(Message.obtain(hander, ImageHander.MSG_PAGE, position, 0));
//                        for (int i = 0; i < imageViews.size(); i++) {
//                            if (i == position % imageViews.size()) {
//                                imageViews.get(i).setBackgroundResource(R.mipmap.ic_dot_default_selected);
//                            } else {
//                                imageViews.get(i).setBackgroundResource(R.mipmap.ic_dot_default_unselected);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//                        switch (state) {
//                            case ViewPager.SCROLL_STATE_DRAGGING:
//                                hander.sendEmptyMessage(ImageHander.MSG_KEEP);
//                                break;
//                            case ViewPager.SCROLL_STATE_IDLE:
//                                hander.sendEmptyMessageDelayed(ImageHander.MSG_UPDATE, ImageHander.MSG_DELAY);
//                                break;
//                        }
//                    }
//                });
//                hander.sendEmptyMessageDelayed(ImageHander.MSG_UPDATE, ImageHander.MSG_DELAY);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "无法连接到网络", Toast.LENGTH_SHORT).show();
            }
        });

        VolleyRequestQueue.getVolleyRequestQueue().addRequest(request);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ll.removeAllViews();
//        imageViews.clear();
    }

    private static class ImageHander extends Handler {
        protected static final int MSG_UPDATE = 1;
        protected static final int MSG_KEEP = 2;
        protected static final int MSG_BREAK = 3;
        protected static final int MSG_PAGE = 4;
        protected static final int MSG_DELAY = 3000;
        private int courrentItem = 0;
        private WeakReference<RecommendFragment> weakReference;

        public ImageHander(WeakReference<RecommendFragment> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecommendFragment fragment = weakReference.get();
            if (fragment.hander.hasMessages(MSG_UPDATE)) {
                fragment.hander.removeMessages(MSG_UPDATE);
            }
            switch (msg.what) {
                case MSG_UPDATE:
                    courrentItem++;
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
