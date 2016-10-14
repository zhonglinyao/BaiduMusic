package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.bean.MusicTopBean;
import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.tools.ImageLoderSetting;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 */
public class MusicTopAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MusicTopBean.TopNameBean> list = new ArrayList<>();
    private MusicTopListener mMusicTopListener;

    public void setMusicTopListener(MusicTopListener musicTopListener) {
        mMusicTopListener = musicTopListener;
    }

    public void setList(ArrayList<MusicTopBean.TopNameBean> list) {
        this.list = list;
    }

    public MusicTopAdapter(Context context) {
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_music_top, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(list.get(position).getPic_s192(), viewHolder.imageView, ImageLoderSetting.getOptions());
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_first.setText(list.get(position).getContent().get(0).getTitle() + "-" +
                list.get(position).getContent().get(0).getAuthor());
        viewHolder.tv_second.setText(list.get(position).getContent().get(1).getTitle() + "-" +
                list.get(position).getContent().get(1).getAuthor());
        viewHolder.tv_third.setText(list.get(position).getContent().get(2).getTitle() + "-" +
                list.get(position).getContent().get(2).getAuthor());
        viewHolder.iv_playAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(list.get(position));
                mMusicTopListener.playAllListener(list.get(position));
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicTopListener.jumpListener(list.get(position));
            }
        });
        return convertView;
    }

    class ViewHolder{

        private final ImageView imageView;
        private final TextView tv_name;
        private final TextView tv_first;
        private final TextView tv_second;
        private final TextView tv_third;
        private final ImageView iv_playAll;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.img_listview_music_top);
            tv_name = (TextView) convertView.findViewById(R.id.tv_listview_name_music_top);
            tv_first = (TextView) convertView.findViewById(R.id.tv_first_listview_music_top);
            tv_second = (TextView) convertView.findViewById(R.id.tv_second_listview_music_top);
            tv_third = (TextView) convertView.findViewById(R.id.tv_third_listview_music_top);
            iv_playAll = (ImageView) convertView.findViewById(R.id.iv_playAll_music_top);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = (int) (MyApp.getWindowWidth() / 4);
            params.height = (int) (MyApp.getWindowWidth() / 4);
            imageView.setLayoutParams(params);
        }
    }
}
