package com.example.lanou3g.baidumusic.musiclibrary.musictop;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.bean.MusicTopSongListBean;
import com.example.lanou3g.baidumusic.bean.PlayMusicTopEvent;
import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.tools.ImageLoderSetting;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.main.playsong.Player;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 */
public class MusicTopSongListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<MusicTopSongListBean.SongListBean> mSongListBeen;
    private final Player mPlayer;
    private PlaySongBean mPlaySongBean;

    public MusicTopSongListAdapter(Context context) {
        mContext = context;
        EventBus.getDefault().register(this);
        mPlayer = Player.getmPlayer();
        mPlaySongBean = mPlayer.getPlaySongBean();
    }

    public void setSongListBeen(List<MusicTopSongListBean.SongListBean> songListBeen) {
        mSongListBeen = songListBeen;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < mSongListBeen.size()) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_musictop_songlist_item, parent, false);
            MusicTopSongListViewHolder viewHolder = new MusicTopSongListViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_footerview_null, parent, false);
            View v = view.findViewById(R.id.footerView);
            ViewGroup.LayoutParams params = v.getLayoutParams();
            params.height = (int) (MyApp.getWindowHeight() / 13);
            v.setLayoutParams(params);
            BottomViewHolder bottomViewHolder = new BottomViewHolder(view);
            return bottomViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position < mSongListBeen.size()) {
            MusicTopSongListViewHolder viewHolder = (MusicTopSongListViewHolder) holder;
            viewHolder.tv_name.setText(mSongListBeen.get(position).getTitle());
            viewHolder.tv_author.setText(mSongListBeen.get(position).getAuthor());

            if (mPlaySongBean != null) {
                if (mPlaySongBean.getSonginfo().getSong_id().equals(mSongListBeen.get(position).getSong_id())) {
                    viewHolder.tv_name.setTextColor(mContext.getResources().getColor(R.color.colorMain));
                    viewHolder.tv_author.setTextColor(mContext.getResources().getColor(R.color.colorMain));
                } else {
                    viewHolder.tv_name.setTextColor(Color.BLACK);
                    viewHolder.tv_author.setTextColor(mContext.getResources().getColor(R.color.colorLine));
                }
            }
            if (position < 9) {
                viewHolder.tv_ranking.setText("0" + (position + 1) + "");
            } else {
                viewHolder.tv_ranking.setText(position + 1 + "");
            }

            if (0 == position) {
                viewHolder.img_ranking.setImageResource(R.mipmap.img_king_mask01);
            } else if (1 == position) {
                viewHolder.img_ranking.setImageResource(R.mipmap.img_king_mask02);
            } else if (2 == position) {
                viewHolder.img_ranking.setImageResource(R.mipmap.img_king_mask03);
            } else {
                viewHolder.img_ranking.setImageResource(R.mipmap.img_king_mask1);
            }
            if (1 == mSongListBeen.get(position).getHas_mv()) {
                viewHolder.iv_mv.setImageResource(R.mipmap.ic_mv);
            }
            ImageLoader.getInstance().displayImage(
                    mSongListBeen.get(position).getPic_big(),
                    viewHolder.img,
                    ImageLoderSetting.getOptions());
            viewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlayMusicTopEvent playMusicTopEvent = new PlayMusicTopEvent();
                    playMusicTopEvent.setItem(position);
                    playMusicTopEvent.setSongListBeen(mSongListBeen);
                    EventBus.getDefault().post(playMusicTopEvent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mSongListBeen == null ? 0 : mSongListBeen.size() + 1;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongInfo(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
        notifyDataSetChanged();
    }

    public class MusicTopSongListViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_author;
        private final ImageView iv_mv;
        private final ImageView iv_sq;
        private final ImageView iv_more;
        private final ImageView iv_singing;
        private final LinearLayout ll;
        private final ImageView img;
        private final ImageView img_ranking;
        private final TextView tv_ranking;

        public MusicTopSongListViewHolder(View itemView) {
            super(itemView);
            tv_ranking = (TextView) itemView.findViewById(R.id.tv_ranking_musictop_songlist_item);
            img_ranking = (ImageView) itemView.findViewById(R.id.iv_srcimg_musictop_songlist_item);
            img = (ImageView) itemView.findViewById(R.id.iv_musictop_songlist_item);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name_musictop_songlist_item);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author_musictop_songlist_item);
            iv_mv = (ImageView) itemView.findViewById(R.id.iv_mv_musictop_songlist_item);
            iv_sq = (ImageView) itemView.findViewById(R.id.iv_sq_musictop_songlist_item);
            iv_more = (ImageView) itemView.findViewById(R.id.iv_more_musictop_songlist_item);
            iv_singing = (ImageView) itemView.findViewById(R.id.iv_singing_musictop_songlist_item);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_musictop_songlist_item);
        }
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
