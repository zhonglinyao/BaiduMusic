package com.example.lanou3g.baidumusic.tools;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.widget.Toast;

import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.bean.DownloadSongBean;
import com.example.lanou3g.baidumusic.bean.LocalMusicBean;
import com.example.lanou3g.baidumusic.bean.PlaySongBean;
import com.example.lanou3g.baidumusic.main.MyApp;
import com.example.lanou3g.baidumusic.values.StringVlaues;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/10/10.
 */
public class Tools {

    private Uri mDownloadUri;
    private ArrayList<Long> downloadIds = new ArrayList<>();

    public static int calculateInSampleSize(BitmapFactory.Options options, int width, int height) {
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int widthInSampleSize = outWidth / width;
        int heightInSampleSize = outHeight / height;
        return Math.max(widthInSampleSize, heightInSampleSize);
    }

    public static String getFormatedDateTime(long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("mm:ss");
        return sDateFormat.format(new Date(dateTime + 0));
    }

    public static Bitmap changackgroundImage(Bitmap sentBitmap, float radius) {
        if (Build.VERSION.SDK_INT > 16 && sentBitmap != null) {
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            final RenderScript rs = RenderScript.create(MyApp.getmContext());
            final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(radius);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        return null;
    }

    public static void showShare() {
        ShareSDK.initSDK(MyApp.getmContext());
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("分享...");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(MyApp.getmContext().getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(MyApp.getmContext());
    }

    public static void downloadSong(final PlaySongBean playSongBean) {
        if (playSongBean != null && playSongBean.getBitrate() != null && playSongBean.getBitrate().getFile_link() != null) {
            Toast.makeText(MyApp.getmContext(), "正在下载", Toast.LENGTH_SHORT).show();
            DownloadManager downloadManager = (DownloadManager) MyApp.getmContext().getSystemService(Context.DOWNLOAD_SERVICE);
            Log.d("Tools", playSongBean.getBitrate().getFile_link());
            Uri mDownloadUri = Uri.parse(playSongBean.getBitrate().getFile_link());
            DownloadManager.Request request = new DownloadManager.Request(mDownloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
            File folder = new File(StringVlaues.downloadPath);
            if (!(folder.exists() && folder.isDirectory())) {
                folder.mkdirs();
            }
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC,
                    playSongBean.getSonginfo().getTitle() + ".mp3");
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
            long downloadId = downloadManager.enqueue(request);
            DownloadSongBean downloadSongBean = new DownloadSongBean();
            downloadSongBean.setDownloadId(downloadId);
            downloadSongBean.setAuthor(playSongBean.getSonginfo().getAuthor());
            downloadSongBean.setTitle(playSongBean.getSonginfo().getTitle());
            downloadSongBean.setSongId(playSongBean.getSonginfo().getSong_id());
            DBtool.getmDBtools().insertDownloadSong(downloadSongBean);
        } else {
            Toast.makeText(MyApp.getmContext(), "网址错误 无法下载", Toast.LENGTH_SHORT).show();
        }
    }

    public static List<LocalMusicBean> localMusicLoader(ContentResolver resolver) {
        ArrayList<LocalMusicBean> localMusicBeen = new ArrayList<>();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor != null) {
            while (cursor.moveToNext()){
                int displayNameCol = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
                int albumCol = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                int idCol = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                int durationCol = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
                int sizeCol = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
                int artistCol = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int urlCol = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                //歌曲名字
                String title = cursor.getString(displayNameCol);
                //歌曲专辑
                String album = cursor.getString(albumCol);

                long id = cursor.getLong(idCol);

                int duration = cursor.getInt(durationCol);

                //歌曲长度
                long size = cursor.getLong(sizeCol);

                //歌曲作者
                String artist = cursor.getString(artistCol);
                String uri = cursor.getString(urlCol);
                if (title != null && uri != null) {
                    LocalMusicBean localMusicBean = new LocalMusicBean(id, title.substring(0, title.length() - 4));
                    localMusicBean.setAlbum(album);
                    localMusicBean.setDuration(duration);
                    localMusicBean.setSize(size);
                    localMusicBean.setArtist(artist);
                    localMusicBean.setUrl(uri);
                    localMusicBeen.add(localMusicBean);
                }
            }
        }
        cursor.close();
        Collections.sort(localMusicBeen);
        return localMusicBeen;
    }
}
