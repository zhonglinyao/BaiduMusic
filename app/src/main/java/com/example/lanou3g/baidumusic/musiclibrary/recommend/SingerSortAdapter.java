package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;

/**
 * Created by dllo on 16/9/26.
 */
public class SingerSortAdapter extends BaseAdapter{
    private Context context;
    private final String[] strings;

    public SingerSortAdapter(Context context) {
        this.context = context;
        strings = RecommendVlaues.sortStrings;
    }

    @Override
    public int getCount() {
        return  strings == null ? 0 : strings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_singer_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(strings[position]);
        return convertView;
    }

    class ViewHolder {

        private final TextView textView;

        public ViewHolder(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_singer_item);
        }
    }
}
