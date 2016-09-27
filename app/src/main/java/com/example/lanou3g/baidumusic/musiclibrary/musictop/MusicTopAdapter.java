package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class MusicTopAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MusicTopBean.TopNameBean> list = new ArrayList<>();
    private final DisplayImageOptions options;

    public void setList(ArrayList<MusicTopBean.TopNameBean> list) {
        this.list = list;
    }

    public MusicTopAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions
               .Builder()
               .showImageForEmptyUri(R.mipmap.default_detail)
               .showImageOnLoading(R.mipmap.default_detail)
               .cacheInMemory(true)
               .cacheOnDisk(true)
               .considerExifParams(true)
               .build();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_music_top, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getPic_s192(), viewHolder.imageView, options);
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_first.setText(list.get(position).getContent().get(0).getTitle() + "-" +
                list.get(position).getContent().get(0).getAuthor());
        viewHolder.tv_second.setText(list.get(position).getContent().get(1).getTitle() + "-" +
                list.get(position).getContent().get(1).getAuthor());
        viewHolder.tv_third.setText(list.get(position).getContent().get(2).getTitle() + "-" +
                list.get(position).getContent().get(2).getAuthor());
        return convertView;
    }

    class ViewHolder{

        private final ImageView imageView;
        private final TextView tv_name;
        private final TextView tv_first;
        private final TextView tv_second;
        private final TextView tv_third;
        private final ImageButton imageButton;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.img_listview_music_top);
            tv_name = (TextView) convertView.findViewById(R.id.tv_listview_name_music_top);
            tv_first = (TextView) convertView.findViewById(R.id.tv_first_listview_music_top);
            tv_second = (TextView) convertView.findViewById(R.id.tv_second_listview_music_top);
            tv_third = (TextView) convertView.findViewById(R.id.tv_third_listview_music_top);
            imageButton = (ImageButton) convertView.findViewById(R.id.btn_listView_music_top);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            params.width = (int) (width / 4);
            params.height = (int) (width / 4);
            imageView.setLayoutParams(params);
        }
    }
}
