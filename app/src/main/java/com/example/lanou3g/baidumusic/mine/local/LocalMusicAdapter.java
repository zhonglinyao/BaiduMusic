package com.example.lanou3g.baidumusic.mine.local;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.LocalMusicBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/15.
 */
public class LocalMusicAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<LocalMusicBean> mLocalMusicBeen;

    public void setLocalMusicBeen(ArrayList<LocalMusicBean> localMusicBeen) {
        mLocalMusicBeen.clear();
        mLocalMusicBeen.addAll(localMusicBeen);
        notifyDataSetChanged();
    }

    public LocalMusicAdapter(Context context) {
        mContext = context;
        mLocalMusicBeen = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mLocalMusicBeen == null ? 0 : mLocalMusicBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mLocalMusicBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LocalViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_local_music_item, parent, false);
            viewHolder = new LocalViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (LocalViewHolder) convertView.getTag();
        }
        viewHolder.mTv_title.setText(mLocalMusicBeen.get(position).getTitle());
        viewHolder.mTv_autnor.setText(mLocalMusicBeen.get(position).getArtist());
        if (mLocalMusicBeen.get(position).getAlbum() == null || mLocalMusicBeen.get(position).getAlbum().length() <= 0){
            viewHolder.mTv_album.setText("未知专辑");
        } else {
            viewHolder.mTv_album.setText("专辑:" + mLocalMusicBeen.get(position).getAlbum());
        }
        return convertView;
    }

    class LocalViewHolder {
        private final TextView mTv_title;
        private final TextView mTv_autnor;
        private final TextView mTv_album;

        public LocalViewHolder(View convertView) {
            mTv_title = (TextView) convertView.findViewById(R.id.tv_title_local_music);
            mTv_autnor = (TextView) convertView.findViewById(R.id.tv_author_local_music);
            mTv_album = (TextView) convertView.findViewById(R.id.tv_album_local_music);
        }
    }
}
