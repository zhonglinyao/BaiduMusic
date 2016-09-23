package com.example.lanou3g.baidumusic.musiclibrary.songmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/21.
 */
public class SongMenuAdapter extends RecyclerView.Adapter<SongMenuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SongMenuBean.ContentBean> arrayList = new ArrayList<>();
    private final DisplayImageOptions options;

    public void setArrayList(ArrayList<SongMenuBean.ContentBean> list) {
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(list.get(i));
        }
        notifyDataSetChanged();
    }

    public SongMenuAdapter(Context context) {
        this.context = context;
        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration
                        .Builder(context)
                        .threadPoolSize(3)
                        .diskCacheFileCount(100)
                        .build();
        ImageLoader.getInstance().init(configuration);
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_song_menu, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(arrayList.get(position).getPic300(), holder.img, options);
        holder.tv_title.setText(arrayList.get(position).getTitle());
        holder.tv_type.setText(arrayList.get(position).getTag());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final ImageButton ib;
        private final TextView tv_title;
        private final TextView tv_type;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_recycleview_songmenu);
            ib = (ImageButton) itemView.findViewById(R.id.ib_recycleview_songmenu);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_reclcleview_songmenu);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type_recycleview_songmenu);
        }
    }
}
