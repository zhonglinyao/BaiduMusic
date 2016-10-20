package com.example.lanou3g.baidumusic.mine.download;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.DownloadSongBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/15.
 */
public class DownloadAdapter extends BaseAdapter{
    private ArrayList<DownloadSongBean> mDownloadSongBeen;
    private Context mContext;

    public DownloadAdapter(Context context) {
        mContext = context;
        mDownloadSongBeen = new ArrayList<>();
    }

    public void setDownloadSongBeen(ArrayList<DownloadSongBean> downloadSongBeen) {
        mDownloadSongBeen.clear();
        mDownloadSongBeen.addAll(downloadSongBeen);
    }

    @Override
    public int getCount() {
        return mDownloadSongBeen == null ? 0 : mDownloadSongBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DownloadViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_download_item, parent, false);
            viewHolder = new DownloadViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DownloadViewHolder) convertView.getTag();
        }
        viewHolder.mTv_title.setText(mDownloadSongBeen.get(position).getTitle());
        viewHolder.mTv_autnor.setText(mDownloadSongBeen.get(position).getAuthor());
        return convertView;
    }

    class  DownloadViewHolder{

        private final TextView mTv_title;
        private final TextView mTv_autnor;

        public DownloadViewHolder(View convertView) {
            mTv_title = (TextView) convertView.findViewById(R.id.tv_title_download);
            mTv_autnor = (TextView) convertView.findViewById(R.id.tv_author_download);
        }
    }
}
