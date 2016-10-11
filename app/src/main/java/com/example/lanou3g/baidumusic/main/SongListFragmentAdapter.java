package com.example.lanou3g.baidumusic.main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/6.
 */
public class SongListFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<MainSongListBean> mSongListBeen;
    private PlaySongBean mPlaySongBean;

    public void setSongListBeen(List<MainSongListBean> songListBeen) {
        mSongListBeen.clear();
        mSongListBeen.addAll(songListBeen);
        notifyDataSetChanged();
    }

    public void setPlaySongBean(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
    }

    public List<MainSongListBean> getSongListBeen() {
        return mSongListBeen;
    }

    public SongListFragmentAdapter(Context context) {
        mContext = context;
        if (mSongListBeen == null){
            mSongListBeen = new ArrayList<>();
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public int getCount() {
        return mSongListBeen == null ? 0 : mSongListBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mSongListBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main_songlist, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mPlaySongBean != null) {
            if (mPlaySongBean.getSonginfo().getSong_id().equals(mSongListBeen.get(position).getSong_id())) {
                viewHolder.mTv_title.setTextColor(mContext.getResources().getColor(R.color.colorMain));
                viewHolder.mTv_author.setTextColor(mContext.getResources().getColor(R.color.colorMain));
            } else {
                viewHolder.mTv_title.setTextColor(Color.BLACK);
                viewHolder.mTv_author.setTextColor(mContext.getResources().getColor(R.color.colorLine));
            }
        }
        viewHolder.mTv_title.setText(mSongListBeen.get(position).getTitle());
        viewHolder.mTv_author.setText(mSongListBeen.get(position).getAuthor());
        viewHolder.mIv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
        return convertView;
    }

    private void delete(int position) {
        mSongListBeen.remove(position);
        notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongInfo(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
        notifyDataSetChanged();
    }

    class ViewHolder {

        private final TextView mTv_title;
        private final TextView mTv_author;
        private final ImageView mIv_delete;

        public ViewHolder(View convertView) {
            mTv_title = (TextView) convertView.findViewById(R.id.tv_title_item_listview_popupwindow);
            mTv_author = (TextView) convertView.findViewById(R.id.tv_author_item_listview_popupwindow);
            mIv_delete = (ImageView) convertView.findViewById(R.id.iv_delete_item_listview_popupwindow);
        }
    }
}
