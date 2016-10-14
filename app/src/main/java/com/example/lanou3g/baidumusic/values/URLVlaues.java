package com.example.lanou3g.baidumusic.values;

/**
 * Created by dllo on 16/5/23.
 */
public class URLVlaues {
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
    public static final String SINGER_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getList&format=json&order=1&limit=12&offset=0&area=0&sex=0&abc=&from=ios&version=5.2.1&from=ios&channel=appstore";
    public static final String NEW_RECOMMEND = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.plaza.index&cuid=8ADCB33F64DBB1F5314036551C922491";

    public static String MUSICTOP_SONGLIST = "";
    private static final String MUSICTOP_SONGLIST_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    private static final String MUSICTOP_SONGLIST_END = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";

    private static String PLAY_SONG = "";
    private static final String PLAY_SONG_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    private static final String PLAY_SONG_END = "&_=1413017198449";

    private static String HOT_SONG_MENU = "";
    private static final String HOT_SONG_MENU_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    private static final String HOT_SONG_MENU_END = "&version=5.2.3&from=ios&channel=appstore";

    public static final String SONGMENU_NEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.ugcdiy.getChanneldiy&param=yjRdAP5CIbiPMNExUqI3SGXU7pV1WOf3frmA3GbLDNz4EszOnoRVPR1VMsmQFXZur20re7LDrJEOpokAOYk0IQksp8rZZZ0UXowy%2BmW1M4fmURfS3wXLHrmTJmEqeS3Y&timestamp=1474958470&sign=0032e3eeb679b5bb5722517930274a27";
//    private static final String SONGMENU_NEW_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=";
//    private static final String SONGMENU_NEW_END = "&page_size=20&query=%E5%85%A8%E9%83%A8";
    public static final String SONGMENU_HOT = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&method=baidu.ting.ugcdiy.getChanneldiy&param=ehU89KYswcMRWNDWGfdd01ugcS8%2B1c891aJ47TFOGx%2FWu9YwjM9BVMZC4pzzHqXFSEbPI3z3BoOyi7hsZ2v%2FjKuV8DCOoHQKVKSdOa4T7hCsNrhDFklfo6iniJSvzBQl&timestamp=1474958544&sign=6432275fd0a0bdbb6678be4fd5764be9";
//    private static final String SONGMENU_HOT_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=";
//    private static final String SONGMENU_HOT_END = "&page_size=20&query=%E5%85%A8%E9%83%A8";

    private static String MV_URL = "";
    private static final String MV_ONE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=1413c&operator=1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=";
    private static final String MV_TWO = "&page_num=";
    private static final String MV_THREE = "&page_size=20&query=%E5%85%A8%E9%83%A8";

    public static String getMusictopSonglist(int type) {
        MUSICTOP_SONGLIST = "";
        MUSICTOP_SONGLIST = MUSICTOP_SONGLIST_HEAD + type + MUSICTOP_SONGLIST_END;
        return MUSICTOP_SONGLIST;
    }

    public static String getMvUrl(Boolean isNew, int pageNmu) {
        if (isNew){
            MV_URL = MV_ONE + 1 + MV_TWO + pageNmu + MV_THREE;
            return MV_URL;
        } else {
            MV_URL = MV_ONE + 0 + MV_TWO + pageNmu + MV_THREE;
            return MV_URL;
        }
    }

    public static String getPlaySong(String song_id) {
        PLAY_SONG = "";
        PLAY_SONG = PLAY_SONG_HEAD + song_id + PLAY_SONG_END;
        return PLAY_SONG;
    }

    public static String getHotSongMenu(String s) {
        HOT_SONG_MENU = "";
        HOT_SONG_MENU = HOT_SONG_MENU_HEAD + s + HOT_SONG_MENU_END;
        return HOT_SONG_MENU;
    }

}
