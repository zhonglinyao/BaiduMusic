package com.example.lanou3g.baidumusic.musiclibrary.songmenu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.bean.HotSongMenuBean;
import com.example.lanou3g.baidumusic.bean.PlaySongMenuEvent;
import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.main.playsong.Player;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class HotSongMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HotSongMenuBean.ContentBean> contentBeen;
    private static final int FOOTER_VIEW = -1;
    private static final int HEAD_VIEW = -2;
    private String text;
    private PlaySongBean mPlaySongBean;
    private Player mPlayer;

    public void setText(String text) {
        this.text = text;
    }

    public void setContentBeen(List<HotSongMenuBean.ContentBean> contentBeen) {
        this.contentBeen = contentBeen;
    }

    public HotSongMenuAdapter(Context context) {
        this.context = context;
        EventBus.getDefault().register(this);
        mPlayer = Player.getmPlayer();
        mPlaySongBean = mPlayer.getPlaySongBean();
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position > contentBeen.size()) {
            return FOOTER_VIEW;
        } else if (0 == position) {
            return HEAD_VIEW;
        } else {
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_footerview_null, parent, false);
            View v = view.findViewById(R.id.footerView);
            ViewGroup.LayoutParams params = v.getLayoutParams();
            params.height = (int) (MyApp.getWindowHeight() / 13);
            v.setLayoutParams(params);
            BottomViewHolder bottomViewHolder = new BottomViewHolder(view);
            return bottomViewHolder;
        } else if (viewType == HEAD_VIEW) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_headview_hot_songmenu, parent, false);
            HeadViewHolder headViewHolder = new HeadViewHolder(view);
            return headViewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_hot_songmenu_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position <= contentBeen.size() && position > 0) {
            final int item = position - 1;
            final ViewHolder viewHolder = (ViewHolder) holder;
            if (mPlaySongBean != null) {
                if (mPlaySongBean.getSonginfo().getSong_id().equals(contentBeen.get(item).getSong_id())) {
                    viewHolder.tv_name.setTextColor(context.getResources().getColor(R.color.colorMain));
                    viewHolder.tv_author.setTextColor(context.getResources().getColor(R.color.colorMain));
                } else {
                    viewHolder.tv_name.setTextColor(Color.BLACK);
                    viewHolder.tv_author.setTextColor(context.getResources().getColor(R.color.colorLine));
                }
            }
            viewHolder.tv_name.setText(contentBeen.get(item).getTitle());
            viewHolder.tv_author.setText(contentBeen.get(item).getAuthor());
            if (1 == contentBeen.get(item).getHas_mv()) {
                viewHolder.iv_mv.setImageResource(R.mipmap.ic_mv);
            }
            if (1 == contentBeen.get(item).getIs_ksong()) {
                viewHolder.iv_singing.setImageResource(R.mipmap.ic_mike_normal);
            }
            viewHolder.Rl.setBackgroundResource(R.drawable.item_select);
            viewHolder.Rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlaySongMenuEvent playSongMenuEvent = new PlaySongMenuEvent();
                    playSongMenuEvent.setItem(item);
                    playSongMenuEvent.setContentBeen(contentBeen);
                    EventBus.getDefault().post(playSongMenuEvent);
                }
            });
        } else if (0 == position) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.tv_head.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return contentBeen == null ? 0 : contentBeen.size() + 2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPlaySongInfo(PlaySongBean playSongBean) {
        mPlaySongBean = playSongBean;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final TextView tv_author;
        private final ImageView iv_mv;
        private final ImageView iv_sq;
        private final ImageView iv_more;
        private final ImageView iv_singing;
        private final RelativeLayout Rl;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name_hot_songmenu);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author_hot_songmenu);
            iv_mv = (ImageView) itemView.findViewById(R.id.iv_mv_hot_songmenu);
            iv_sq = (ImageView) itemView.findViewById(R.id.iv_sq_hot_songmenu);
            iv_more = (ImageView) itemView.findViewById(R.id.iv_more_hot_songmenu);
            iv_singing = (ImageView) itemView.findViewById(R.id.iv_singing_songmenu);
            Rl = (RelativeLayout) itemView.findViewById(R.id.rl_hot_songmenu_item);

        }
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_head;

        public HeadViewHolder(View itemView) {
            super(itemView);
            tv_head = (TextView) itemView.findViewById(R.id.tv_head_hot_songmenu);
        }
    }


}
