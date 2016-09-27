package com.example.lanou3g.baidumusic;

/**
 * Created by dllo on 16/5/23.
 */
public class URLVlaues {
    public static final String RECOMMEND_CAROUSE = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.plaza.getFocusPic&format=json&from=ios&version=5.2.3&from=ios&channel=appstore";
    public static final String RECOMMAND_HOT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.getHotGeDanAndOfficial&num=4&version=5.2.3&from=ios&channel=appstore";
    public static final String MUSIC_TOP = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";
    public static final String SONGMENU = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";
    public static final String SONGLIST_DETAIL_Front = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    public static final String SONGLIST_DETAIL_BEHIND = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    public static final String TOP_SONG_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    public static final String TOP_SONG_BEHIND = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    public static final String RECOMMAND_CAROUSE_SONG_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.album.getAlbumInfo&album_id=";
    public static final String RECOMMAND_CAROUSE_SONG_BEHIND = "&format=json&from=ios&version=5.2.5&from=ios&channel=appstore";
    public static final String PLAY_FRONT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    public static final String PLAY_BEHIND = "&_=1413017198449";
    public static final String RECOMMEND = "http://url.cn/28a65ZG";
    public static final String SINGER_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getList&format=json&order=1&limit=12&offset=0&area=0&sex=0&abc=&from=ios&version=5.2.1&from=ios&channel=appstore";
    public static final String NEW_RECOMMEND = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.plaza.index&cuid=8ADCB33F64DBB1F5314036551C922491";

    private static String HOT_SONG_MENU = "";
    private static final String HOT_SONG_MENU_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    private static final String HOT_SONG_MENU_END = "&version=5.2.3&from=ios&channel=appstore";
    public static String getHotSongMenu(String s) {
        HOT_SONG_MENU = HOT_SONG_MENU_HEAD + s + HOT_SONG_MENU_END;
        return HOT_SONG_MENU;
    }
}
