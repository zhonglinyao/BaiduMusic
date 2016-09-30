package com.example.lanou3g.baidumusic.musiclibrary.songmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.musiclibrary.recommend.SongMenuListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/21.
 */

public class SongMenuAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SongMenuBean.DiyInfoBean> list;
    private final DisplayImageOptions options;
    private SongMenuListener songMenuListener;

    public void setSongMenuListener(SongMenuListener songMenuListener) {
        this.songMenuListener = songMenuListener;
    }

    public void setList(List<SongMenuBean.DiyInfoBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public SongMenuAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.default_artist)
                .showImageOnLoading(R.mipmap.default_artist)
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
        if (viewType >= list.size()) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_footerview_null, parent, false);
            View v = view.findViewById(R.id.footerView);
            ViewGroup.LayoutParams params = v.getLayoutParams();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            int h = metrics.heightPixels;
            params.height = (int) (h / 13);
            v.setLayoutParams(params);
            BottomViewHolder bottomViewHolder = new BottomViewHolder(view);
            return bottomViewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_song_menu, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position < list.size()) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ImageLoader.getInstance().displayImage(list.get(position).getList_pic(), viewHolder.img, options);
            viewHolder.tv_title.setText(list.get(position).getTitle());
            viewHolder.tv_type.setText("by " + list.get(position).getUsername());
            viewHolder.tv_listener_count.setText(list.get(position).getListen_num() + "");
            viewHolder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    songMenuListener.callBack(list.get(position).getList_id());
                }
            });
            viewHolder.iv_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    songMenuListener.playCallBack(list.get(position).getList_id());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null || list.size() == 0 ? 0 : list.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageView iv_play;
        private final TextView tv_title;
        private final TextView tv_type;
        private final TextView tv_listener_count;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_recycleview_songmenu);
            iv_play = (ImageView) itemView.findViewById(R.id.iv_play_recycleview_songmenu);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_reclcleview_songmenu);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type_recycleview_songmenu);
            tv_listener_count = (TextView) itemView.findViewById(R.id.tv_listener_count);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            params.width = width / 2;
            params.height = width / 2;
            img.setLayoutParams(params);
        }
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
