package com.example.lanou3g.baidumusic.musiclibrary.mv;

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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class MVadapter extends RecyclerView.Adapter {
    private final DisplayImageOptions options;
    private Context context;
    private List<MVBean.ResultBean.MvListBean> mvListBeen;

    public void setMvListBeen(List<MVBean.ResultBean.MvListBean> mvListBeen) {
        this.mvListBeen = mvListBeen;
        notifyDataSetChanged();
    }

    public MVadapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.default_mv)
                .showImageOnLoading(R.mipmap.default_mv)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_mv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ImageLoader.getInstance().displayImage(mvListBeen.get(position).getThumbnail(), viewHolder.img, options);
        viewHolder.tv_title.setText(mvListBeen.get(position).getTitle());
        viewHolder.tv_author.setText(mvListBeen.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return mvListBeen == null ? 0 : mvListBeen.size();
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
}
