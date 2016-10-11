package com.example.lanou3g.baidumusic.musiclibrary.mv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.ImageLoderSetting;
import com.example.lanou3g.baidumusic.musiclibrary.recommend.RefreshListenerCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class MVadapter extends RecyclerView.Adapter {
    private Context context;
    private List<MVBean.ResultBean.MvListBean> mvListBeen;
    private RefreshListenerCallBack refreshListener;
    private int FOOTER_VIEW = 0;
    private int ITEM_VIEW = 1;
    private int haveMore;
    private int pageNum;
    private boolean isNow;

    public void setIsNow(boolean isNow) {
        this.isNow = isNow;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setHaveMore(int haveMore) {
        this.haveMore = haveMore;
    }

    public void setMvListBeen(List<MVBean.ResultBean.MvListBean> mvListBeen) {
        this.mvListBeen.clear();
        this.mvListBeen.addAll(mvListBeen);
        notifyDataSetChanged();
    }
    public void addMvListBean(List<MVBean.ResultBean.MvListBean> listBeen) {
        mvListBeen.addAll(listBeen);
        notifyDataSetChanged();
    }

    public void setRefreshListener(RefreshListenerCallBack refreshListener) {
        this.refreshListener = refreshListener;
    }

    public MVadapter(Context context) {
        this.context = context;
        mvListBeen = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mvListBeen.size()){
            return ITEM_VIEW;
        } else {
            return FOOTER_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_mv, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.refresh_footer_view, parent, false);
            BottomViewHolder bottomViewHolder = new BottomViewHolder(view);
            return bottomViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mvListBeen.size()) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ImageLoader.getInstance().displayImage(mvListBeen.get(position).getThumbnail(), viewHolder.img, ImageLoderSetting.getMvOptions());
            viewHolder.tv_title.setText(mvListBeen.get(position).getTitle());
            viewHolder.tv_author.setText(mvListBeen.get(position).getArtist());
        } else {
            final BottomViewHolder bottomViewHolder = (BottomViewHolder) holder;
            if (haveMore != 1) {
                bottomViewHolder.tv.setText("没有更多");
            }
            bottomViewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshListener.refresh(bottomViewHolder.tv, pageNum, haveMore, isNow);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mvListBeen == null || mvListBeen.size() == 0 ? 0 : mvListBeen.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv_title;
        private final TextView tv_author;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_item_mv);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_item_mv);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author_item_mv);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            params.width = width / 2;
            params.height = (int) (width / 3.5);
            img.setLayoutParams(params);
        }
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout ll;
        private final ImageView iv;
        private final TextView tv;

        public BottomViewHolder(View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_refresh_footer_view);
            iv = (ImageView) itemView.findViewById(R.id.iv_refresh_footer_view);
            tv = (TextView) itemView.findViewById(R.id.tv_refresh_footer_view);
            ViewGroup.LayoutParams params = tv.getLayoutParams();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            int h = metrics.heightPixels;
            params.height = h / 9;
            tv.setLayoutParams(params);
        }
    }
}
