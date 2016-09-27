package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/22.
 */

public class RecommendAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<List> lists;
    private ArrayList<RecommendBean.ModuleBean> moduleBeen = new ArrayList<>();
    private final int[] layout;
    private final DisplayImageOptions options;
    private ImageHander hander;
    private static ViewPager vp;
    private ListenerCallBack listenerCallBack;

    public void setListenerCallBack(ListenerCallBack listenerCallBack) {
        this.listenerCallBack = listenerCallBack;
    }

    public void setModuleBeen(ArrayList<RecommendBean.ModuleBean> moduleBeen) {
        this.moduleBeen = moduleBeen;
        notifyDataSetChanged();
    }

    public void setLists(ArrayList<List> lists) {
        this.lists = lists;
    }

    public RecommendAdapter(Context context) {
        this.context = context;
        hander = new ImageHander(new WeakReference<RecommendAdapter>(this));
        layout = new int[]{
                R.layout.item1_carouse_image, R.layout.item2_recycleview_recommend,
                R.layout.item_siximg_recycleview_recommend, R.layout.item_siximg_recycleview_recommend,
                R.layout.item_threeimg_recycleview_recommend, R.layout.item_advertisement_recycleview_recommend,
                R.layout.item_broadcasting_recycleview_recommend, R.layout.item_recsong_recycleview_recommend,
                R.layout.item_threeimg_recycleview_recommend, R.layout.item_siximg_recycleview_recommend,
                R.layout.item_siximg_recycleview_recommend, R.layout.item_column_recycleview_recommend};

        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.default_live_ic)
                .showImageOnLoading(R.mipmap.default_live_ic)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout[viewType], parent, false);
        switch (viewType) {
            case 0:
                ViewHolderCarouse viewHolderCarouse = new ViewHolderCarouse(view);
                return viewHolderCarouse;
            case 1:
                ViewHolderEntry viewHolderEntry = new ViewHolderEntry(view);
                return viewHolderEntry;
            case 2:
                ViewHolderSixImg ViewHolderSixImgRecsong = new ViewHolderSixImg(view);
                return ViewHolderSixImgRecsong;
            case 3:
                ViewHolderSixImg ViewHolderSixImgMix1 = new ViewHolderSixImg(view);
                return ViewHolderSixImgMix1;
            case 4:
                ViewHolderThreeImg viewHolderThreeImgMix22 = new ViewHolderThreeImg(view);
                return viewHolderThreeImgMix22;
            case 5:
                ViewHolderAdvertisement viewHolderAdvertisement = new ViewHolderAdvertisement(view);
                return viewHolderAdvertisement;
            case 6:
                ViewHolderScene viewHolderScene = new ViewHolderScene(view);
                return viewHolderScene;
            case 7:
                ViewHolderRecsong viewHolderRecsong = new ViewHolderRecsong(view);
                return viewHolderRecsong;
            case 8:
                ViewHolderThreeImg viewHolderThreeImgMix9 = new ViewHolderThreeImg(view);
                return viewHolderThreeImgMix9;
            case 9:
                ViewHolderSixImg viewHolderSixImgMix5 = new ViewHolderSixImg(view);
                return viewHolderSixImgMix5;
            case 10:
                ViewHolderSixImg viewHolderSixImgRadio = new ViewHolderSixImg(view);
                return viewHolderSixImgRadio;
            case 11:
                ViewHolderColumn viewHolderColumn = new ViewHolderColumn(view);
                return viewHolderColumn;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
                if (MyApp.isNetworkAvailable()){
                    ViewHolderCarouse viewHolderCarouse = (ViewHolderCarouse) holder;
                    CarouseAdapter adapter = new CarouseAdapter(context);
                    adapter.setFocusResultBeen(lists.get(position));
                    vp.setAdapter(adapter);
                    vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            hander.sendMessage(Message.obtain(hander, ImageHander.MSG_PAGE, position, 0));
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

                    hander.sendEmptyMessageDelayed(ImageHander.MSG_UPDATE, ImageHander.MSG_DELAY);
                }else {

                }
                break;
            case 1:
                ViewHolderEntry viewHolderEntry = (ViewHolderEntry) holder;
                viewHolderEntry.ll_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listenerCallBack.callBack(RecommendVlaues.BTN_SORT_ONE);
                    }
                });
                viewHolderEntry.ll_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listenerCallBack.callBack(RecommendVlaues.BTN_SORT_TWO);
                    }
                });
                viewHolderEntry.ll_three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listenerCallBack.callBack(RecommendVlaues.BTN_SORT_THREE);
                    }
                });
                break;
            case 2:
                final List<RecommendBean.ResultBean.DiyBean.DiyResultBean> diyResultBeen = lists.get(position);
                ViewHolderSixImg viewHolderSixImgDiy = (ViewHolderSixImg) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderSixImgDiy.img_head, options);
                viewHolderSixImgDiy.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderSixImgDiy.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderSixImgDiy.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 6; i++) {
                    ImageLoader.getInstance().displayImage(diyResultBeen.get(i).getPic(), viewHolderSixImgDiy.imageViews[i], options);
                    viewHolderSixImgDiy.titleTextViews[i].setText(diyResultBeen.get(i).getTitle());
                    final int finalI = i;
                    viewHolderSixImgDiy.imageViews[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listenerCallBack.hotSongMenu(diyResultBeen.get(finalI).getListid());
                        }
                    });
                }


                break;
            case 3:
                List<RecommendBean.ResultBean.Mix1Bean.Mix1ResultBean> mix1ResultBeen = lists.get(position);
                ViewHolderSixImg viewHolderSixImgMix1 = (ViewHolderSixImg) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderSixImgMix1.img_head, options);
                viewHolderSixImgMix1.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderSixImgMix1.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderSixImgMix1.tv_head_more.setVisibility(View.INVISIBLE);
                }

                for (int i = 0; i < 6; i++) {
                    ImageLoader.getInstance().displayImage(mix1ResultBeen.get(i).getPic(), viewHolderSixImgMix1.imageViews[i], options);
                    viewHolderSixImgMix1.titleTextViews[i].setText(mix1ResultBeen.get(i).getTitle());
                    viewHolderSixImgMix1.authorTextViews[i].setText(mix1ResultBeen.get(i).getAuthor());
                }
                break;
            case 4:
                List<RecommendBean.ResultBean.Mix22Bean.Mix22ResultBean> mix22ResultBeen = lists.get(position);
                ViewHolderThreeImg viewHolderThreeImgMix22 = (ViewHolderThreeImg) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderThreeImgMix22.img_head, options);
                viewHolderThreeImgMix22.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderThreeImgMix22.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderThreeImgMix22.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 3; i++) {
                    ImageLoader.getInstance().displayImage(mix22ResultBeen.get(i).getPic(), viewHolderThreeImgMix22.imageViews[i], options);
                    viewHolderThreeImgMix22.titleTextViews[i].setText(mix22ResultBeen.get(i).getTitle());
                    viewHolderThreeImgMix22.authorTextViews[i].setText(mix22ResultBeen.get(i).getAuthor());
                }
                break;
            case 5:
                List<RecommendBean.ResultBean.AdSmallBean.AdSmallResultBean> adSmallResultBeen = lists.get(position);
                ViewHolderAdvertisement viewHolderAdvertisement = (ViewHolderAdvertisement) holder;
                ImageLoader.getInstance().displayImage(adSmallResultBeen.get(0).getPic(), viewHolderAdvertisement.imgAdvertisement, options);
                break;
            case 6:
                List<RecommendBean.ResultBean.SceneBean.SceneResultBean.ActionBean> actionBeen = lists.get(position);
                ViewHolderScene viewHolderScene = (ViewHolderScene) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderScene.img_head, options);
                viewHolderScene.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderScene.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderScene.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 4; i++) {
                    ImageLoader.getInstance().displayImage(actionBeen.get(i).getIcon_android(), viewHolderScene.imageViews[i], options);
                    viewHolderScene.textViews[i].setText(actionBeen.get(i).getScene_name());
                }
                break;
            case 7:
                List<RecommendBean.ResultBean.RecsongBean.RecsongResultBean> recsongResultBeen = lists.get(position);
                ViewHolderRecsong viewHolderRecsong = (ViewHolderRecsong) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderRecsong.img_head, options);
                viewHolderRecsong.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderRecsong.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderRecsong.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 3; i++) {
                    ImageLoader.getInstance().displayImage(recsongResultBeen.get(i).getPic_premium(), viewHolderRecsong.cricleImages[i]);
                    viewHolderRecsong.titleTextViews[i].setText(recsongResultBeen.get(i).getTitle());
                    viewHolderRecsong.authorTextViews[i].setText(recsongResultBeen.get(i).getAuthor());
                }
                break;
            case 8:
                List<RecommendBean.ResultBean.Mix9Bean.Mix9ResultBean> mix9ResultBeen = lists.get(position);
                ViewHolderThreeImg viewHolderThreeImgMix9 = (ViewHolderThreeImg) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderThreeImgMix9.img_head, options);
                viewHolderThreeImgMix9.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderThreeImgMix9.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderThreeImgMix9.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 3; i++) {
                    ImageLoader.getInstance().displayImage(mix9ResultBeen.get(i).getPic(), viewHolderThreeImgMix9.imageViews[i], options);
                    viewHolderThreeImgMix9.titleTextViews[i].setText(mix9ResultBeen.get(i).getTitle());
                }
                break;
            case 9:
                List<RecommendBean.ResultBean.Mix5Bean.Mix5ResultBean> mix5ResultBeen = lists.get(position);
                ViewHolderSixImg viewHolderSixImgMix5 = (ViewHolderSixImg) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderSixImgMix5.img_head, options);
                viewHolderSixImgMix5.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderSixImgMix5.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderSixImgMix5.tv_head_more.setVisibility(View.INVISIBLE);
                }

                for (int i = 0; i < 6; i++) {
                    ImageLoader.getInstance().displayImage(mix5ResultBeen.get(i).getPic(), viewHolderSixImgMix5.imageViews[i], options);
                    viewHolderSixImgMix5.titleTextViews[i].setText(mix5ResultBeen.get(i).getTitle());
                    viewHolderSixImgMix5.authorTextViews[i].setText(mix5ResultBeen.get(i).getAuthor());
                }
                break;
            case 10:
                List<RecommendBean.ResultBean.RadioBean.RadioResultBean> radioResultBeen = lists.get(position);
                ViewHolderSixImg viewHolderSixImgRadio = (ViewHolderSixImg) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderSixImgRadio.img_head, options);
                viewHolderSixImgRadio.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderSixImgRadio.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderSixImgRadio.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 6; i++) {
                    ImageLoader.getInstance().displayImage(radioResultBeen.get(i).getPic(), viewHolderSixImgRadio.imageViews[i], options);
                    viewHolderSixImgRadio.titleTextViews[i].setText(radioResultBeen.get(i).getTitle());
                }
                break;
            case 11:
                List<RecommendBean.ResultBean.Mod7Bean.Mod7ResultBean> mod7ResultBeen = lists.get(position);
                ViewHolderColumn viewHolderColumn = (ViewHolderColumn) holder;
                ImageLoader.getInstance().displayImage(moduleBeen.get(position).getPicurl(), viewHolderColumn.img_head, options);
                viewHolderColumn.tv_head_title.setText(moduleBeen.get(position).getTitle());
                if (IsMore(moduleBeen.get(position).getTitle_more())) {
                    viewHolderColumn.tv_head_more.setText(moduleBeen.get(position).getTitle_more());
                } else {
                    viewHolderColumn.tv_head_more.setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < 4; i++) {
                    ImageLoader.getInstance().displayImage(mod7ResultBeen.get(i).getPic(), viewHolderColumn.imageViews[i], options);
                    viewHolderColumn.titleTextViews[i].setText(mod7ResultBeen.get(i).getTitle());
                    viewHolderColumn.authorTextViews[i].setText(mod7ResultBeen.get(i).getDesc());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    class ViewHolderCarouse extends RecyclerView.ViewHolder {

        public ViewHolderCarouse(View itemView) {
            super(itemView);
            vp = bindView(R.id.vp_carouse, itemView);
        }
    }

    class ViewHolderEntry extends RecyclerView.ViewHolder {

        private final LinearLayout ll_one;
        private final LinearLayout ll_two;
        private final LinearLayout ll_three;
        private final LinearLayout ll_four;

        public ViewHolderEntry(View itemView) {
            super(itemView);
            ll_one = bindView(R.id.ll_entry_one_recommend, itemView);
            ll_two = bindView(R.id.ll_entry_two_recommend, itemView);
            ll_three = bindView(R.id.ll_entry_three_recommend, itemView);
            ll_four = bindView(R.id.ll_entry_four_recommend, itemView);

        }
    }

    class ViewHolderSixImg extends RecyclerView.ViewHolder {

        private final ImageView img_head;
        private final TextView tv_head_title;
        private final TextView tv_head_more;
        private final ImageView[] imageViews;
        private final TextView[] titleTextViews;
        private final TextView[] authorTextViews;

        public ViewHolderSixImg(View itemView) {
            super(itemView);
            View viewHead = bindView(R.id.ic_siximg_head_recommend, itemView);
            img_head = bindView(R.id.img_head_recommend, viewHead);
            tv_head_title = bindView(R.id.tv_head_title_recommend, viewHead);
            tv_head_more = bindView(R.id.tv_head_more_recommend, viewHead);
            View viewFirstImg = bindView(R.id.ic_first_siximg_recommend, itemView);
            ImageView img_one = bindView(R.id.img_one_recommend, viewFirstImg);
            ImageView img_two = bindView(R.id.img_two_recommend, viewFirstImg);
            ImageView img_three = bindView(R.id.img_three_recommend, viewFirstImg);
            TextView tv_title_one = bindView(R.id.tv_title_one_recommend, viewFirstImg);
            TextView tv_title_two = bindView(R.id.tv_title_two_recommend, viewFirstImg);
            TextView tv_title_three = bindView(R.id.tv_title_three_recommend, viewFirstImg);
            TextView tv_author_one = bindView(R.id.tv_author_one_recommend, viewFirstImg);
            TextView tv_author_two = bindView(R.id.tv_author_two_recommend, viewFirstImg);
            TextView tv_author_three = bindView(R.id.tv_author_three_recommend, viewFirstImg);
            View viewSecondImg = bindView(R.id.ic_second_siximg_recommend, itemView);
            ImageView img_four = bindView(R.id.img_one_recommend, viewSecondImg);
            ImageView img_fiv = bindView(R.id.img_two_recommend, viewSecondImg);
            ImageView img_six = bindView(R.id.img_three_recommend, viewSecondImg);
            TextView tv_title_four = bindView(R.id.tv_title_one_recommend, viewSecondImg);
            TextView tv_title_five = bindView(R.id.tv_title_two_recommend, viewSecondImg);
            TextView tv_title_six = bindView(R.id.tv_title_three_recommend, viewSecondImg);
            TextView tv_author_four = bindView(R.id.tv_author_one_recommend, viewSecondImg);
            TextView tv_author_five = bindView(R.id.tv_author_two_recommend, viewSecondImg);
            TextView tv_author_six = bindView(R.id.tv_author_three_recommend, viewSecondImg);
            imageViews = new ImageView[]{img_one, img_two, img_three, img_four, img_fiv, img_six};
            titleTextViews = new TextView[]{tv_title_one, tv_title_two, tv_title_three, tv_title_four, tv_title_five, tv_title_six};
            authorTextViews = new TextView[]{tv_author_one, tv_author_two, tv_author_three, tv_author_four, tv_author_five, tv_author_six};
        }
    }

    class ViewHolderThreeImg extends RecyclerView.ViewHolder {

        private final ImageView[] imageViews;
        private final TextView[] titleTextViews;
        private final ImageView img_head;
        private final TextView tv_head_title;
        private final TextView tv_head_more;
        private final TextView[] authorTextViews;

        public ViewHolderThreeImg(View itemView) {
            super(itemView);
            View viewHead = bindView(R.id.ic_threeimg_head_recommend, itemView);
            img_head = bindView(R.id.img_head_recommend, viewHead);
            tv_head_title = bindView(R.id.tv_head_title_recommend, viewHead);
            tv_head_more = bindView(R.id.tv_head_more_recommend, viewHead);
            View viewImage = bindView(R.id.ic_threeimg_recommend, itemView);
            ImageView img_one = bindView(R.id.img_one_recommend, viewImage);
            ImageView img_two = bindView(R.id.img_two_recommend, viewImage);
            ImageView img_three = bindView(R.id.img_three_recommend, viewImage);
            TextView tv_one = bindView(R.id.tv_title_one_recommend, viewImage);
            TextView tv_two = bindView(R.id.tv_title_two_recommend, viewImage);
            TextView tv_three = bindView(R.id.tv_title_three_recommend, viewImage);
            TextView tv_author_one = bindView(R.id.tv_author_one_recommend, viewImage);
            TextView tv_author_two = bindView(R.id.tv_author_two_recommend, viewImage);
            TextView tv_author_three = bindView(R.id.tv_author_three_recommend, viewImage);
            imageViews = new ImageView[]{img_one, img_two, img_three};
            titleTextViews = new TextView[]{tv_one, tv_two, tv_three};
            authorTextViews = new TextView[]{tv_author_one, tv_author_two, tv_author_three};
        }
    }

    class ViewHolderAdvertisement extends RecyclerView.ViewHolder {

        private final ImageView imgAdvertisement;

        public ViewHolderAdvertisement(View itemView) {
            super(itemView);
            imgAdvertisement = bindView(R.id.iv_advertisement_recommend, itemView);
        }
    }

    class ViewHolderScene extends RecyclerView.ViewHolder {

        private final ImageView[] imageViews;
        private final ImageView img_head;
        private final TextView tv_head_title;
        private final TextView tv_head_more;
        private final TextView[] textViews;

        public ViewHolderScene(View itemView) {
            super(itemView);
            View view = bindView(R.id.ic_breadcasting_recommend, itemView);
            img_head = bindView(R.id.img_head_recommend, view);
            tv_head_title = bindView(R.id.tv_head_title_recommend, view);
            tv_head_more = bindView(R.id.tv_head_more_recommend, view);
            ImageView img_one = bindView(R.id.iv_one_breadcasting_recommend, itemView);
            ImageView img_two = bindView(R.id.iv_two_breadcasting_recommend, itemView);
            ImageView img_three = bindView(R.id.iv_three_breadcasting_recommend, itemView);
            ImageView img_four = bindView(R.id.iv_four_breadcasting_recommend, itemView);
            TextView tv_one = bindView(R.id.tv_one_breadcasting_recommend, itemView);
            TextView tv_two = bindView(R.id.tv_two_breadcasting_recommend, itemView);
            TextView tv_three = bindView(R.id.tv_three_breadcasting_recommend, itemView);
            TextView tv_four = bindView(R.id.tv_four_breadcasting_recommend, itemView);
            imageViews = new ImageView[]{img_one, img_two, img_three, img_four};
            textViews = new TextView[]{tv_one, tv_two, tv_three, tv_four};
        }
    }

    class ViewHolderRecsong extends RecyclerView.ViewHolder {

        private final TextView tv_head_title;
        private final TextView tv_head_more;
        private final ImageView img_head;
        private final CircleImageView[] cricleImages;
        private final TextView[] titleTextViews;
        private final TextView[] authorTextViews;

        public ViewHolderRecsong(View itemView) {
            super(itemView);
            View viewHead = bindView(R.id.ic_nowsong_head_recommend, itemView);
            tv_head_title = bindView(R.id.tv_head_title_recommend, viewHead);
            tv_head_more = bindView(R.id.tv_head_more_recommend, viewHead);
            img_head = bindView(R.id.img_head_recommend, viewHead);
            View viewOne = bindView(R.id.ic_nowsong_one_recommend, itemView);
            CircleImageView cricleImageOne = bindView(R.id.cricle_image_recsong_layout, viewOne);
            TextView tv_title_one = bindView(R.id.tv_recsong_title, viewOne);
            TextView tv_author_one = bindView(R.id.tv_recsong_author, viewOne);
            View viewTwo = bindView(R.id.ic_nowsong_two_recommend, itemView);
            CircleImageView cricleImageTwo = bindView(R.id.cricle_image_recsong_layout, viewTwo);
            TextView tv_title_two = bindView(R.id.tv_recsong_title, viewTwo);
            TextView tv_author_two = bindView(R.id.tv_recsong_author, viewTwo);
            View viewThree = bindView(R.id.ic_nowsong_three_recommend, itemView);
            CircleImageView cricleImageThree = bindView(R.id.cricle_image_recsong_layout, viewThree);
            TextView tv_title_three = bindView(R.id.tv_recsong_title, viewThree);
            TextView tv_author_three = bindView(R.id.tv_recsong_author, viewThree);
            cricleImages = new CircleImageView[]{cricleImageOne, cricleImageTwo, cricleImageThree};
            titleTextViews = new TextView[]{tv_title_one, tv_title_two, tv_title_three};
            authorTextViews = new TextView[]{tv_author_one, tv_author_two, tv_author_three};
        }
    }

    class ViewHolderColumn extends RecyclerView.ViewHolder {

        private final ImageView[] imageViews;
        private final TextView[] titleTextViews;
        private final TextView[] authorTextViews;
        private final ImageView img_head;
        private final TextView tv_head_title;
        private final TextView tv_head_more;

        public ViewHolderColumn(View itemView) {
            super(itemView);

            View viewHead = bindView(R.id.ic_column_head_recommend, itemView);
            img_head = bindView(R.id.img_head_recommend, viewHead);
            tv_head_title = bindView(R.id.tv_head_title_recommend, viewHead);
            tv_head_more = bindView(R.id.tv_head_more_recommend, viewHead);

            View viewOne = bindView(R.id.ic_column_one_recommend, itemView);
            ImageView img_one = bindView(R.id.iv_column_layout, viewOne);
            TextView tv_title_one = bindView(R.id.tv_column_title, viewOne);
            TextView tv_author_one = bindView(R.id.tv_column_author, viewOne);

            View viewTwo = bindView(R.id.ic_column_two_recommend, itemView);
            ImageView img_two = bindView(R.id.iv_column_layout, viewTwo);
            TextView tv_title_two = bindView(R.id.tv_column_title, viewTwo);
            TextView tv_author_two = bindView(R.id.tv_column_author, viewTwo);

            View viewThree = bindView(R.id.ic_column_three_recommend, itemView);
            ImageView img_three = bindView(R.id.iv_column_layout, viewThree);
            TextView tv_title_three = bindView(R.id.tv_column_title, viewThree);
            TextView tv_author_three = bindView(R.id.tv_column_author, viewThree);

            View viewFour = bindView(R.id.ic_column_four_recommend, itemView);
            ImageView img_four = bindView(R.id.iv_column_layout, viewFour);
            TextView tv_title_four = bindView(R.id.tv_column_title, viewFour);
            TextView tv_author_four = bindView(R.id.tv_column_author, viewFour);

            imageViews = new ImageView[]{img_one, img_two, img_three, img_four};
            titleTextViews = new TextView[]{tv_title_one, tv_title_two, tv_title_three, tv_title_four};
            authorTextViews = new TextView[]{tv_author_one, tv_author_two, tv_author_three, tv_author_four};
        }
    }

    protected <T extends View> T bindView(int id, View view) {
        return (T) view.findViewById(id);
    }

    private Boolean IsMore(String titleMore) {
        if (titleMore.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static class ImageHander extends Handler {
        protected static final int MSG_UPDATE = 1;
        protected static final int MSG_KEEP = 2;
        protected static final int MSG_BREAK = 3;
        protected static final int MSG_PAGE = 4;
        protected static final int MSG_DELAY = 3000;
        private int courrentItem = 0;
        private WeakReference<RecommendAdapter> weakReference;

        public ImageHander(WeakReference<RecommendAdapter> weakReference) {
            this.weakReference = weakReference;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecommendAdapter adapter = weakReference.get();
            if (adapter.hander.hasMessages(MSG_UPDATE)) {
                adapter.hander.removeMessages(MSG_UPDATE);
            }
            switch (msg.what) {
                case MSG_UPDATE:
                    courrentItem++;
                    vp.setCurrentItem(courrentItem);
                    adapter.hander.sendEmptyMessageDelayed(MSG_UPDATE, MSG_DELAY);
                    break;
                case MSG_KEEP:
                    break;
                case MSG_BREAK:
                    adapter.hander.sendEmptyMessageDelayed(MSG_UPDATE, MSG_DELAY);
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
