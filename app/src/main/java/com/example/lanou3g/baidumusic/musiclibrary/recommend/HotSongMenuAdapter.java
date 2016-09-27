package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class HotSongMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<HotSongMenuBean.ContentBean> contentBeen;
    private static final int FOOTER_VIEW = -1;

    public void setContentBeen(List<HotSongMenuBean.ContentBean> contentBeen) {
        this.contentBeen = contentBeen;
    }

    public HotSongMenuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position >= contentBeen.size()) {
            return FOOTER_VIEW;
        } else {
            return position;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW){
            View view = LayoutInflater.from(context).inflate(R.layout.layout_footerview, parent, false);
            View v = view.findViewById(R.id.footerView);
            ViewGroup.LayoutParams params = v.getLayoutParams();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            params.height = (int) (width / 7);
            v.setLayoutParams(params);
            BottomViewHolder bottomViewHolder = new BottomViewHolder(view);
            return bottomViewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_hot_songmenu_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < contentBeen.size()){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_name.setText(contentBeen.get(position).getTitle());
            viewHolder.tv_author.setText(contentBeen.get(position).getAuthor());
            if (1 == contentBeen.get(position).getHas_mv()) {
                viewHolder.iv_mv.setImageResource(R.mipmap.ic_mv);
            }
            if (1 == contentBeen.get(position).getIs_ksong()) {
                viewHolder.iv_singing.setImageResource(R.mipmap.ic_mike_normal);
            }
            if (2 == contentBeen.get(position).getHavehigh()) {
                viewHolder.iv_sq.setImageResource(R.mipmap.ic_sq);
            }
        }

    }

    @Override
    public int getItemCount() {
        return contentBeen == null ? 0 : contentBeen.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_author;
        private final ImageView iv_mv;
        private final ImageView iv_sq;
        private final ImageButton iv_more;
        private final ImageView iv_singing;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name_hot_songmenu);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author_hot_songmenu);
            iv_mv = (ImageView) itemView.findViewById(R.id.iv_mv_hot_songmenu);
            iv_sq = (ImageView) itemView.findViewById(R.id.iv_sq_hot_songmenu);
            iv_more = (ImageButton) itemView.findViewById(R.id.iv_more_hot_songmenu);
            iv_singing = (ImageView) itemView.findViewById(R.id.iv_singing_songmenu);

        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
