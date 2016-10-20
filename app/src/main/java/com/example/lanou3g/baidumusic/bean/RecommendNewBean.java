package com.example.lanou3g.baidumusic.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dllo on 16/9/22.
 */
public class RecommendNewBean {


    private ResultBean result;

    private int error_code;


    private List<ModuleBean> module;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ModuleBean> getModule() {
        return module;
    }

    public void setModule(List<ModuleBean> module) {
        this.module = module;
    }

    public static class ResultBean {

        @SerializedName("mix_1")
        private Mix1Bean mix1;


        private FocusBean focus;


        @SerializedName("mix_22")
        private Mix22Bean mix22;

        private EntryBean entry;


        private SceneBean scene;

        @SerializedName("mix_5")
        private Mix5Bean mix5;


        @SerializedName("mod_7")
        private Mod7Bean mod7;

        private RecsongBean recsong;

        private RadioBean radio;

        private DiyBean diy;

        @SerializedName("ad_small")
        private AdSmallBean adSmall;

        @SerializedName("mod_26")
        private Mod26Bean mod26;

        @SerializedName("mix_9")
        private Mix9Bean mix9;

        public Mix1Bean getMix1() {
            return mix1;
        }

        public void setMix1(Mix1Bean mix1) {
            this.mix1 = mix1;
        }

        public FocusBean getFocus() {
            return focus;
        }

        public void setFocus(FocusBean focus) {
            this.focus = focus;
        }

        public Mix22Bean getMix22() {
            return mix22;
        }

        public void setMix22(Mix22Bean mix22) {
            this.mix22 = mix22;
        }

        public EntryBean getEntry() {
            return entry;
        }

        public void setEntry(EntryBean entry) {
            this.entry = entry;
        }

        public SceneBean getScene() {
            return scene;
        }

        public void setScene(SceneBean scene) {
            this.scene = scene;
        }

        public Mix5Bean getMix5() {
            return mix5;
        }

        public void setMix5(Mix5Bean mix5) {
            this.mix5 = mix5;
        }

        public Mod7Bean getMod7() {
            return mod7;
        }

        public void setMod7(Mod7Bean mod7) {
            this.mod7 = mod7;
        }

        public RecsongBean getRecsong() {
            return recsong;
        }

        public void setRecsong(RecsongBean recsong) {
            this.recsong = recsong;
        }

        public RadioBean getRadio() {
            return radio;
        }

        public void setRadio(RadioBean radio) {
            this.radio = radio;
        }

        public DiyBean getDiy() {
            return diy;
        }

        public void setDiy(DiyBean diy) {
            this.diy = diy;
        }

        public AdSmallBean getAdSmall() {
            return adSmall;
        }

        public void setAdSmall(AdSmallBean adSmall) {
            this.adSmall = adSmall;
        }

        public Mod26Bean getMod26() {
            return mod26;
        }

        public void setMod26(Mod26Bean mod26) {
            this.mod26 = mod26;
        }

        public Mix9Bean getMix9() {
            return mix9;
        }

        public void setMix9(Mix9Bean mix9) {
            this.mix9 = mix9;
        }

        public static class Mix1Bean {
            @SerializedName("error_code")
            private int mix1ErrorCode;

            @SerializedName("result")
            private List<Mix1ResultBean> mix1Result;

            public int getMix1ErrorCode() {
                return mix1ErrorCode;
            }

            public void setMix1ErrorCode(int mix1ErrorCode) {
                this.mix1ErrorCode = mix1ErrorCode;
            }

            public List<Mix1ResultBean> getMix1Result() {
                return mix1Result;
            }

            public void setMix1Result(List<Mix1ResultBean> mix1Result) {
                this.mix1Result = mix1Result;
            }

            public static class Mix1ResultBean {
                @SerializedName("desc")
                private String mix1Desc;
                @SerializedName("pic")
                private String mix1Pic;
                @SerializedName("type_id")
                private String mix1TypeId;
                @SerializedName("type")
                private int mix1Type;
                @SerializedName("title")
                private String mix1Title;
                @SerializedName("tip_type")
                private int mix1TipType;
                @SerializedName("author")
                private String mix1Author;

                public String getMix1Desc() {
                    return mix1Desc;
                }

                public void setMix1Desc(String mix1Desc) {
                    this.mix1Desc = mix1Desc;
                }

                public String getMix1Pic() {
                    return mix1Pic;
                }

                public void setMix1Pic(String mix1Pic) {
                    this.mix1Pic = mix1Pic;
                }

                public String getMix1TypeId() {
                    return mix1TypeId;
                }

                public void setMix1TypeId(String mix1TypeId) {
                    this.mix1TypeId = mix1TypeId;
                }

                public int getMix1Type() {
                    return mix1Type;
                }

                public void setMix1Type(int mix1Type) {
                    this.mix1Type = mix1Type;
                }

                public String getMix1Title() {
                    return mix1Title;
                }

                public void setMix1Title(String mix1Title) {
                    this.mix1Title = mix1Title;
                }

                public int getMix1TipType() {
                    return mix1TipType;
                }

                public void setMix1TipType(int mix1TipType) {
                    this.mix1TipType = mix1TipType;
                }

                public String getMix1Author() {
                    return mix1Author;
                }

                public void setMix1Author(String mix1Author) {
                    this.mix1Author = mix1Author;
                }
            }
        }

        public static class FocusBean {
            @SerializedName("error_code")
            private int focusErrorCode;


            @SerializedName("result")
            private List<FocusResultBean> focusResult;

            public int getFocusErrorCode() {
                return focusErrorCode;
            }

            public void setFocusErrorCode(int focusErrorCode) {
                this.focusErrorCode = focusErrorCode;
            }

            public List<FocusResultBean> getFocusResult() {
                return focusResult;
            }

            public void setFocusResult(List<FocusResultBean> focusResult) {
                this.focusResult = focusResult;
            }

            public static class FocusResultBean {
                @SerializedName("randpic")
                private String focusRandpic;
                @SerializedName("code")
                private String focusCode;
                @SerializedName("mo_type")
                private int focusMoType;
                @SerializedName("type")
                private int focusType;
                @SerializedName("is_publish")
                private String focusIsPublish;
                @SerializedName("randpic_iphone6")
                private String focusRandpicIphone6;
                @SerializedName("randpic_desc")
                private String focusRandpicDesc;

                public String getFocusRandpic() {
                    return focusRandpic;
                }

                public void setFocusRandpic(String focusRandpic) {
                    this.focusRandpic = focusRandpic;
                }

                public String getFocusCode() {
                    return focusCode;
                }

                public void setFocusCode(String focusCode) {
                    this.focusCode = focusCode;
                }

                public int getFocusMoType() {
                    return focusMoType;
                }

                public void setFocusMoType(int focusMoType) {
                    this.focusMoType = focusMoType;
                }

                public int getFocusType() {
                    return focusType;
                }

                public void setFocusType(int focusType) {
                    this.focusType = focusType;
                }

                public String getFocusIsPublish() {
                    return focusIsPublish;
                }

                public void setFocusIsPublish(String focusIsPublish) {
                    this.focusIsPublish = focusIsPublish;
                }

                public String getFocusRandpicIphone6() {
                    return focusRandpicIphone6;
                }

                public void setFocusRandpicIphone6(String focusRandpicIphone6) {
                    this.focusRandpicIphone6 = focusRandpicIphone6;
                }

                public String getFocusRandpicDesc() {
                    return focusRandpicDesc;
                }

                public void setFocusRandpicDesc(String focusRandpicDesc) {
                    this.focusRandpicDesc = focusRandpicDesc;
                }
            }
        }

        public static class Mix22Bean {
            @SerializedName("error_code")
            private int mix22ErrorCode;

            @SerializedName("result")
            private List<Mix22ResultBean> mix22Result;

            public int getMix22ErrorCode() {
                return mix22ErrorCode;
            }

            public void setMix22ErrorCode(int mix22ErrorCode) {
                this.mix22ErrorCode = mix22ErrorCode;
            }

            public List<Mix22ResultBean> getMix22Result() {
                return mix22Result;
            }

            public void setMix22Result(List<Mix22ResultBean> mix22Result) {
                this.mix22Result = mix22Result;
            }

            public static class Mix22ResultBean {
                @SerializedName("desc")
                private String mix22Desc;
                @SerializedName("pic")
                private String mix22Pic;
                @SerializedName("type_id")
                private String mix22TypeId;
                @SerializedName("type")
                private int mix22Type;
                @SerializedName("title")
                private String mix22Title;
                @SerializedName("tip_type")
                private int mix22TipType;
                @SerializedName("author")
                private String mix22Author;

                public String getMix22Desc() {
                    return mix22Desc;
                }

                public void setMix22Desc(String mix22Desc) {
                    this.mix22Desc = mix22Desc;
                }

                public String getMix22Pic() {
                    return mix22Pic;
                }

                public void setMix22Pic(String mix22Pic) {
                    this.mix22Pic = mix22Pic;
                }

                public String getMix22TypeId() {
                    return mix22TypeId;
                }

                public void setMix22TypeId(String mix22TypeId) {
                    this.mix22TypeId = mix22TypeId;
                }

                public int getMix22Type() {
                    return mix22Type;
                }

                public void setMix22Type(int mix22Type) {
                    this.mix22Type = mix22Type;
                }

                public String getMix22Title() {
                    return mix22Title;
                }

                public void setMix22Title(String mix22Title) {
                    this.mix22Title = mix22Title;
                }

                public int getMix22TipType() {
                    return mix22TipType;
                }

                public void setMix22TipType(int mix22TipType) {
                    this.mix22TipType = mix22TipType;
                }

                public String getMix22Author() {
                    return mix22Author;
                }

                public void setMix22Author(String mix22Author) {
                    this.mix22Author = mix22Author;
                }
            }
        }

        public static class EntryBean {
            @SerializedName("error_code")
            private int entryErrorCode;

            @SerializedName("result")
            private List<EntryResultBean> entryResult;

            public int getEntryErrorCode() {
                return entryErrorCode;
            }

            public void setEntryErrorCode(int entryErrorCode) {
                this.entryErrorCode = entryErrorCode;
            }

            public List<EntryResultBean> getEntryResult() {
                return entryResult;
            }

            public void setEntryResult(List<EntryResultBean> entryResult) {
                this.entryResult = entryResult;
            }

            public static class EntryResultBean {
                @SerializedName("day")
                private String entryDay;
                @SerializedName("title")
                private String entryTitle;
                @SerializedName("icon")
                private String entryIcon;
                @SerializedName("jump")
                private String entryJump;

                public String getEntryDay() {
                    return entryDay;
                }

                public void setEntryDay(String entryDay) {
                    this.entryDay = entryDay;
                }

                public String getEntryTitle() {
                    return entryTitle;
                }

                public void setEntryTitle(String entryTitle) {
                    this.entryTitle = entryTitle;
                }

                public String getEntryIcon() {
                    return entryIcon;
                }

                public void setEntryIcon(String entryIcon) {
                    this.entryIcon = entryIcon;
                }

                public String getEntryJump() {
                    return entryJump;
                }

                public void setEntryJump(String entryJump) {
                    this.entryJump = entryJump;
                }
            }
        }

        public static class SceneBean {
            @SerializedName("errno")
            private int sceneErrno;
            @SerializedName("error_code")
            private int sceneErrorCode;
            @SerializedName("error_message")
            private String sceneErrorMessage;

            public int getSceneErrno() {
                return sceneErrno;
            }

            public void setSceneErrno(int sceneErrno) {
                this.sceneErrno = sceneErrno;
            }

            public int getSceneErrorCode() {
                return sceneErrorCode;
            }

            public void setSceneErrorCode(int sceneErrorCode) {
                this.sceneErrorCode = sceneErrorCode;
            }

            public String getSceneErrorMessage() {
                return sceneErrorMessage;
            }

            public void setSceneErrorMessage(String sceneErrorMessage) {
                this.sceneErrorMessage = sceneErrorMessage;
            }
        }

        public static class Mix5Bean {
            @SerializedName("error_code")
            private int mix5ErrorCode;

            @SerializedName("result")
            private List<Mix5ResultBean> mix5Result;

            public int getMix5ErrorCode() {
                return mix5ErrorCode;
            }

            public void setMix5ErrorCode(int mix5ErrorCode) {
                this.mix5ErrorCode = mix5ErrorCode;
            }

            public List<Mix5ResultBean> getMix5Result() {
                return mix5Result;
            }

            public void setMix5Result(List<Mix5ResultBean> mix5Result) {
                this.mix5Result = mix5Result;
            }

            public static class Mix5ResultBean {
                @SerializedName("desc")
                private String mix5Desc;
                @SerializedName("pic")
                private String mix5Pic;
                @SerializedName("type_id")
                private String mix5TypeId;
                @SerializedName("type")
                private int mix5Type;
                @SerializedName("title")
                private String mix5Title;
                @SerializedName("tip_type")
                private int mix5TipType;
                @SerializedName("author")
                private String mix5Author;

                public String getMix5Desc() {
                    return mix5Desc;
                }

                public void setMix5Desc(String mix5Desc) {
                    this.mix5Desc = mix5Desc;
                }

                public String getMix5Pic() {
                    return mix5Pic;
                }

                public void setMix5Pic(String mix5Pic) {
                    this.mix5Pic = mix5Pic;
                }

                public String getMix5TypeId() {
                    return mix5TypeId;
                }

                public void setMix5TypeId(String mix5TypeId) {
                    this.mix5TypeId = mix5TypeId;
                }

                public int getMix5Type() {
                    return mix5Type;
                }

                public void setMix5Type(int mix5Type) {
                    this.mix5Type = mix5Type;
                }

                public String getMix5Title() {
                    return mix5Title;
                }

                public void setMix5Title(String mix5Title) {
                    this.mix5Title = mix5Title;
                }

                public int getMix5TipType() {
                    return mix5TipType;
                }

                public void setMix5TipType(int mix5TipType) {
                    this.mix5TipType = mix5TipType;
                }

                public String getMix5Author() {
                    return mix5Author;
                }

                public void setMix5Author(String mix5Author) {
                    this.mix5Author = mix5Author;
                }
            }
        }

        public static class Mod7Bean {
            @SerializedName("error_code")
            private int mod7ErrorCode;


            @SerializedName("result")
            private List<Mod7ResultBean> mod7Result;

            public int getMod7ErrorCode() {
                return mod7ErrorCode;
            }

            public void setMod7ErrorCode(int mod7ErrorCode) {
                this.mod7ErrorCode = mod7ErrorCode;
            }

            public List<Mod7ResultBean> getMod7Result() {
                return mod7Result;
            }

            public void setMod7Result(List<Mod7ResultBean> mod7Result) {
                this.mod7Result = mod7Result;
            }

            public static class Mod7ResultBean {
                @SerializedName("desc")
                private String mod7Desc;
                @SerializedName("pic")
                private String mod7Pic;
                @SerializedName("type_id")
                private String mod7TypeId;
                @SerializedName("type")
                private int mod7Type;
                @SerializedName("title")
                private String mod7Title;
                @SerializedName("tip_type")
                private int mod7TipType;
                @SerializedName("author")
                private String mod7Author;

                public String getMod7Desc() {
                    return mod7Desc;
                }

                public void setMod7Desc(String mod7Desc) {
                    this.mod7Desc = mod7Desc;
                }

                public String getMod7Pic() {
                    return mod7Pic;
                }

                public void setMod7Pic(String mod7Pic) {
                    this.mod7Pic = mod7Pic;
                }

                public String getMod7TypeId() {
                    return mod7TypeId;
                }

                public void setMod7TypeId(String mod7TypeId) {
                    this.mod7TypeId = mod7TypeId;
                }

                public int getMod7Type() {
                    return mod7Type;
                }

                public void setMod7Type(int mod7Type) {
                    this.mod7Type = mod7Type;
                }

                public String getMod7Title() {
                    return mod7Title;
                }

                public void setMod7Title(String mod7Title) {
                    this.mod7Title = mod7Title;
                }

                public int getMod7TipType() {
                    return mod7TipType;
                }

                public void setMod7TipType(int mod7TipType) {
                    this.mod7TipType = mod7TipType;
                }

                public String getMod7Author() {
                    return mod7Author;
                }

                public void setMod7Author(String mod7Author) {
                    this.mod7Author = mod7Author;
                }
            }
        }

        public static class RecsongBean {
            @SerializedName("error_code")
            private int recsongErrorCode;

            @SerializedName("result")
            private List<RecsongResultBean> recsongResult;

            public int getRecsongErrorCode() {
                return recsongErrorCode;
            }

            public void setRecsongErrorCode(int recsongErrorCode) {
                this.recsongErrorCode = recsongErrorCode;
            }

            public List<RecsongResultBean> getRecsongResult() {
                return recsongResult;
            }

            public void setRecsongResult(List<RecsongResultBean> recsongResult) {
                this.recsongResult = recsongResult;
            }

            public static class RecsongResultBean {
                @SerializedName("resource_type_ext")
                private String recsongResourceTypeExt;
                @SerializedName("learn")
                private String recsongLearn;
                @SerializedName("del_status")
                private String recsongDelStatus;
                @SerializedName("korean_bb_song")
                private String recsongKoreanBbSong;
                @SerializedName("versions")
                private String recsongVersions;
                @SerializedName("title")
                private String recsongTitle;
                @SerializedName("bitrate_fee")
                private String recsongBitrateFee;
                @SerializedName("song_id")
                private String recsongSongId;
                @SerializedName("has_mv_mobile")
                private String recsongHasMvMobile;
                @SerializedName("pic_premium")
                private String recsongPicPremium;
                @SerializedName("author")
                private String recsongAuthor;

                public String getRecsongResourceTypeExt() {
                    return recsongResourceTypeExt;
                }

                public void setRecsongResourceTypeExt(String recsongResourceTypeExt) {
                    this.recsongResourceTypeExt = recsongResourceTypeExt;
                }

                public String getRecsongLearn() {
                    return recsongLearn;
                }

                public void setRecsongLearn(String recsongLearn) {
                    this.recsongLearn = recsongLearn;
                }

                public String getRecsongDelStatus() {
                    return recsongDelStatus;
                }

                public void setRecsongDelStatus(String recsongDelStatus) {
                    this.recsongDelStatus = recsongDelStatus;
                }

                public String getRecsongKoreanBbSong() {
                    return recsongKoreanBbSong;
                }

                public void setRecsongKoreanBbSong(String recsongKoreanBbSong) {
                    this.recsongKoreanBbSong = recsongKoreanBbSong;
                }

                public String getRecsongVersions() {
                    return recsongVersions;
                }

                public void setRecsongVersions(String recsongVersions) {
                    this.recsongVersions = recsongVersions;
                }

                public String getRecsongTitle() {
                    return recsongTitle;
                }

                public void setRecsongTitle(String recsongTitle) {
                    this.recsongTitle = recsongTitle;
                }

                public String getRecsongBitrateFee() {
                    return recsongBitrateFee;
                }

                public void setRecsongBitrateFee(String recsongBitrateFee) {
                    this.recsongBitrateFee = recsongBitrateFee;
                }

                public String getRecsongSongId() {
                    return recsongSongId;
                }

                public void setRecsongSongId(String recsongSongId) {
                    this.recsongSongId = recsongSongId;
                }

                public String getRecsongHasMvMobile() {
                    return recsongHasMvMobile;
                }

                public void setRecsongHasMvMobile(String recsongHasMvMobile) {
                    this.recsongHasMvMobile = recsongHasMvMobile;
                }

                public String getRecsongPicPremium() {
                    return recsongPicPremium;
                }

                public void setRecsongPicPremium(String recsongPicPremium) {
                    this.recsongPicPremium = recsongPicPremium;
                }

                public String getRecsongAuthor() {
                    return recsongAuthor;
                }

                public void setRecsongAuthor(String recsongAuthor) {
                    this.recsongAuthor = recsongAuthor;
                }
            }
        }

        public static class RadioBean {
            @SerializedName("error_code")
            private int radioErrorCode;

            @SerializedName("result")
            private List<RadioResultBean> radioResult;

            public int getRadioErrorCode() {
                return radioErrorCode;
            }

            public void setRadioErrorCode(int radioErrorCode) {
                this.radioErrorCode = radioErrorCode;
            }

            public List<RadioResultBean> getRadioResult() {
                return radioResult;
            }

            public void setRadioResult(List<RadioResultBean> radioResult) {
                this.radioResult = radioResult;
            }

            public static class RadioResultBean {
                @SerializedName("desc")
                private String radioDesc;
                @SerializedName("itemid")
                private String radioItemid;
                @SerializedName("title")
                private String radioTitle;
                @SerializedName("album_id")
                private String radioAlbumId;
                @SerializedName("type")
                private String radioType;
                @SerializedName("channelid")
                private String radioChannelid;
                @SerializedName("pic")
                private String radioPic;

                public String getRadioDesc() {
                    return radioDesc;
                }

                public void setRadioDesc(String radioDesc) {
                    this.radioDesc = radioDesc;
                }

                public String getRadioItemid() {
                    return radioItemid;
                }

                public void setRadioItemid(String radioItemid) {
                    this.radioItemid = radioItemid;
                }

                public String getRadioTitle() {
                    return radioTitle;
                }

                public void setRadioTitle(String radioTitle) {
                    this.radioTitle = radioTitle;
                }

                public String getRadioAlbumId() {
                    return radioAlbumId;
                }

                public void setRadioAlbumId(String radioAlbumId) {
                    this.radioAlbumId = radioAlbumId;
                }

                public String getRadioType() {
                    return radioType;
                }

                public void setRadioType(String radioType) {
                    this.radioType = radioType;
                }

                public String getRadioChannelid() {
                    return radioChannelid;
                }

                public void setRadioChannelid(String radioChannelid) {
                    this.radioChannelid = radioChannelid;
                }

                public String getRadioPic() {
                    return radioPic;
                }

                public void setRadioPic(String radioPic) {
                    this.radioPic = radioPic;
                }
            }
        }

        public static class DiyBean {
            @SerializedName("error_code")
            private int diyErrorCode;

            @SerializedName("result")
            private List<DiyResultBean> diyResult;

            public int getDiyErrorCode() {
                return diyErrorCode;
            }

            public void setDiyErrorCode(int diyErrorCode) {
                this.diyErrorCode = diyErrorCode;
            }

            public List<DiyResultBean> getDiyResult() {
                return diyResult;
            }

            public void setDiyResult(List<DiyResultBean> diyResult) {
                this.diyResult = diyResult;
            }

            public static class DiyResultBean {
                @SerializedName("position")
                private int diyPosition;
                @SerializedName("tag")
                private String diyTag;
                @SerializedName("pic")
                private String diypPic;
                @SerializedName("title")
                private String diyTitle;
                @SerializedName("collectnum")
                private int diyCollectnum;
                @SerializedName("type")
                private String diyType;
                @SerializedName("listenum")
                private int diyListenum;
                @SerializedName("listid")
                private String diyListid;
                @SerializedName("songidlist")
                private List<?> diySongidlist;

                public int getDiyPosition() {
                    return diyPosition;
                }

                public void setDiyPosition(int diyPosition) {
                    this.diyPosition = diyPosition;
                }

                public String getDiyTag() {
                    return diyTag;
                }

                public void setDiyTag(String diyTag) {
                    this.diyTag = diyTag;
                }

                public String getDiypPic() {
                    return diypPic;
                }

                public void setDiypPic(String diypPic) {
                    this.diypPic = diypPic;
                }

                public String getDiyTitle() {
                    return diyTitle;
                }

                public void setDiyTitle(String diyTitle) {
                    this.diyTitle = diyTitle;
                }

                public int getDiyCollectnum() {
                    return diyCollectnum;
                }

                public void setDiyCollectnum(int diyCollectnum) {
                    this.diyCollectnum = diyCollectnum;
                }

                public String getDiyType() {
                    return diyType;
                }

                public void setDiyType(String diyType) {
                    this.diyType = diyType;
                }

                public int getDiyListenum() {
                    return diyListenum;
                }

                public void setDiyListenum(int diyListenum) {
                    this.diyListenum = diyListenum;
                }

                public String getDiyListid() {
                    return diyListid;
                }

                public void setDiyListid(String diyListid) {
                    this.diyListid = diyListid;
                }

                public List<?> getDiySongidlist() {
                    return diySongidlist;
                }

                public void setDiySongidlist(List<?> diySongidlist) {
                    this.diySongidlist = diySongidlist;
                }
            }
        }

        public static class AdSmallBean {
            @SerializedName("error_code")
            private int adSmallErrorCode;

            @SerializedName("result")
            private List<AdSmallResultBean> adSmallResult;

            public int getAdSmallErrorCode() {
                return adSmallErrorCode;
            }

            public void setAdSmallErrorCode(int adSmallErrorCode) {
                this.adSmallErrorCode = adSmallErrorCode;
            }

            public List<AdSmallResultBean> getAdSmallResult() {
                return adSmallResult;
            }

            public void setAdSmallResult(List<AdSmallResultBean> adSmallResult) {
                this.adSmallResult = adSmallResult;
            }

            public static class AdSmallResultBean {
                @SerializedName("desc")
                private String adSmallDesc;
                @SerializedName("pic")
                private String adSmallPic;
                @SerializedName("type_id")
                private String adSmallTypeId;
                @SerializedName("type")
                private int adSmallType;
                @SerializedName("title")
                private String adSmallTitle;
                @SerializedName("tip_type")
                private int adSmallTipType;
                @SerializedName("author")
                private String adSmallAuthor;

                public String getAdSmallDesc() {
                    return adSmallDesc;
                }

                public void setAdSmallDesc(String adSmallDesc) {
                    this.adSmallDesc = adSmallDesc;
                }

                public String getAdSmallPic() {
                    return adSmallPic;
                }

                public void setAdSmallPic(String adSmallPic) {
                    this.adSmallPic = adSmallPic;
                }

                public String getAdSmallTypeId() {
                    return adSmallTypeId;
                }

                public void setAdSmallTypeId(String adSmallTypeId) {
                    this.adSmallTypeId = adSmallTypeId;
                }

                public int getAdSmallType() {
                    return adSmallType;
                }

                public void setAdSmallType(int adSmallType) {
                    this.adSmallType = adSmallType;
                }

                public String getAdSmallTitle() {
                    return adSmallTitle;
                }

                public void setAdSmallTitle(String adSmallTitle) {
                    this.adSmallTitle = adSmallTitle;
                }

                public int getAdSmallTipType() {
                    return adSmallTipType;
                }

                public void setAdSmallTipType(int adSmallTipType) {
                    this.adSmallTipType = adSmallTipType;
                }

                public String getAdSmallAuthor() {
                    return adSmallAuthor;
                }

                public void setAdSmallAuthor(String adSmallAuthor) {
                    this.adSmallAuthor = adSmallAuthor;
                }
            }
        }

        public static class Mod26Bean {
            @SerializedName("error_code")
            private int mod26ErrorCode;

            @SerializedName("result")
            private List<Mod26ResultBean> mod26Result;

            public int getMod26ErrorCode() {
                return mod26ErrorCode;
            }

            public void setMod26ErrorCode(int mod26ErrorCode) {
                this.mod26ErrorCode = mod26ErrorCode;
            }

            public List<Mod26ResultBean> getMod26Result() {
                return mod26Result;
            }

            public void setMod26Result(List<Mod26ResultBean> mod26Result) {
                this.mod26Result = mod26Result;
            }

            public static class Mod26ResultBean {
                @SerializedName("desc")
                private String mod26Desc;
                @SerializedName("pic")
                private String mod26Pic;
                @SerializedName("type_id")
                private String mod26TypeId;
                @SerializedName("type")
                private int mod26Type;
                @SerializedName("title")
                private String mod26Title;
                @SerializedName("tip_type")
                private int mod26TipType;
                @SerializedName("author")
                private String mod26Author;

                public String getMod26Desc() {
                    return mod26Desc;
                }

                public void setMod26Desc(String mod26Desc) {
                    this.mod26Desc = mod26Desc;
                }

                public String getMod26Pic() {
                    return mod26Pic;
                }

                public void setMod26Pic(String mod26Pic) {
                    this.mod26Pic = mod26Pic;
                }

                public String getMod26TypeId() {
                    return mod26TypeId;
                }

                public void setMod26TypeId(String mod26TypeId) {
                    this.mod26TypeId = mod26TypeId;
                }

                public int getMod26Type() {
                    return mod26Type;
                }

                public void setMod26Type(int mod26Type) {
                    this.mod26Type = mod26Type;
                }

                public String getMod26Title() {
                    return mod26Title;
                }

                public void setMod26Title(String mod26Title) {
                    this.mod26Title = mod26Title;
                }

                public int getMod26TipType() {
                    return mod26TipType;
                }

                public void setMod26TipType(int mod26TipType) {
                    this.mod26TipType = mod26TipType;
                }

                public String getMod26Author() {
                    return mod26Author;
                }

                public void setMod26Author(String mod26Author) {
                    this.mod26Author = mod26Author;
                }
            }
        }

        public static class Mix9Bean {
            @SerializedName("error_code")
            private int mix9ErrorCode;

            @SerializedName("result")
            private List<Mix9ResultBean> mix9Result;

            public int getMix9ErrorCode() {
                return mix9ErrorCode;
            }

            public void setMix9ErrorCode(int mix9ErrorCode) {
                this.mix9ErrorCode = mix9ErrorCode;
            }

            public List<Mix9ResultBean> getMix9Result() {
                return mix9Result;
            }

            public void setMix9Result(List<Mix9ResultBean> mix9Result) {
                this.mix9Result = mix9Result;
            }

            public static class Mix9ResultBean {
                @SerializedName("desc")
                private String mix9Desc;
                @SerializedName("pic")
                private String mix9Pic;
                @SerializedName("type_id")
                private String mix9TypeId;
                @SerializedName("type")
                private int mix9Type;
                @SerializedName("title")
                private String mix9Title;
                @SerializedName("tip_type")
                private int mix9TipType;
                @SerializedName("author")
                private String mix9Author;

                public String getMix9Desc() {
                    return mix9Desc;
                }

                public void setMix9Desc(String mix9Desc) {
                    this.mix9Desc = mix9Desc;
                }

                public String getMix9Pic() {
                    return mix9Pic;
                }

                public void setMix9Pic(String mix9Pic) {
                    this.mix9Pic = mix9Pic;
                }

                public String getMix9TypeId() {
                    return mix9TypeId;
                }

                public void setMix9TypeId(String mix9TypeId) {
                    this.mix9TypeId = mix9TypeId;
                }

                public int getMix9Type() {
                    return mix9Type;
                }

                public void setMix9Type(int mix9Type) {
                    this.mix9Type = mix9Type;
                }

                public String getMix9Title() {
                    return mix9Title;
                }

                public void setMix9Title(String mix9Title) {
                    this.mix9Title = mix9Title;
                }

                public int getMix9TipType() {
                    return mix9TipType;
                }

                public void setMix9TipType(int mix9TipType) {
                    this.mix9TipType = mix9TipType;
                }

                public String getMix9Author() {
                    return mix9Author;
                }

                public void setMix9Author(String mix9Author) {
                    this.mix9Author = mix9Author;
                }
            }
        }
    }

    public static class ModuleBean {
        @SerializedName("link_url")
        private String moduleLinkUrl;
        @SerializedName("pos")
        private int modulePos;
        @SerializedName("title")
        private String moduleTitle;
        @SerializedName("key")
        private String moduleKey;
        @SerializedName("picurl")
        private String modulePicurl;
        @SerializedName("title_more")
        private String moduleTitleMore;
        @SerializedName("style")
        private int moduleStyle;
        @SerializedName("jump")
        private String moduleJump;

        public String getModuleLinkUrl() {
            return moduleLinkUrl;
        }

        public void setModuleLinkUrl(String moduleLinkUrl) {
            this.moduleLinkUrl = moduleLinkUrl;
        }

        public int getModulePos() {
            return modulePos;
        }

        public void setModulePos(int modulePos) {
            this.modulePos = modulePos;
        }

        public String getModuleTitle() {
            return moduleTitle;
        }

        public void setModuleTitle(String moduleTitle) {
            this.moduleTitle = moduleTitle;
        }

        public String getModuleKey() {
            return moduleKey;
        }

        public void setModuleKey(String moduleKey) {
            this.moduleKey = moduleKey;
        }

        public String getModulePicurl() {
            return modulePicurl;
        }

        public void setModulePicurl(String modulePicurl) {
            this.modulePicurl = modulePicurl;
        }

        public String getModuleTitleMore() {
            return moduleTitleMore;
        }

        public void setModuleTitleMore(String moduleTitleMore) {
            this.moduleTitleMore = moduleTitleMore;
        }

        public int getModuleStyle() {
            return moduleStyle;
        }

        public void setModuleStyle(int moduleStyle) {
            this.moduleStyle = moduleStyle;
        }

        public String getModuleJump() {
            return moduleJump;
        }

        public void setModuleJump(String moduleJump) {
            this.moduleJump = moduleJump;
        }
    }
}
