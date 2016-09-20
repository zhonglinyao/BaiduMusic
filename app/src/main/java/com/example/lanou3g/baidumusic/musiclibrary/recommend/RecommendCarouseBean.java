package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class RecommendCarouseBean {

    /**
     * pic : [{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14743544044acbfec7623a1341ab583a93adeca8a4.jpg","randpic_ios5":"","randpic_desc":"果酱开放日","randpic_ipad":"","randpic_qq":"","randpic_2":"bos_client_147435440887f7ed5fbf2bb5df2eeff493025425de","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147435440887f7ed5fbf2bb5df2eeff493025425de.jpg","special_type":0,"ipad_desc":"果酱开放日","is_publish":"111100","mo_type":"5","type":7,"code":"354504733"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147430046199bf7db1f76c43d702ad2755b1bc7f6f.jpg","randpic_ios5":"","randpic_desc":"很不低调","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474300470706bbbbfa53c78b84fd06d5d0435d9d2.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147430047562fc19edcc9e6b2498842de41a1dd864.jpg","randpic_2":"bos_client_147430046685174b38f486aff817ca966503b8f752","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147430046685174b38f486aff817ca966503b8f752.jpg","special_type":0,"ipad_desc":"很不低调","is_publish":"111101","mo_type":"2","type":2,"code":"271663956"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14742970336f9a7d455b53cfde9b3bebd18124cf60.jpg","randpic_ios5":"","randpic_desc":"云画的月光 OST Part 6","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474297041b0a883b81054a8603ea42446224ccd61.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474297047b694303c5a42d8d6114aaa351ee9a2f9.jpg","randpic_2":"bos_client_147429703621a6b9fbf10899f013bc78ead161697a","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147429703621a6b9fbf10899f013bc78ead161697a.jpg","special_type":0,"ipad_desc":"云画的月光 OST Part 6","is_publish":"111101","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/song_buy/index/index.html?type=2&type_id=271659864&unchangeable"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474182525e598365b036496a7650f6b460a4da4b6.jpg","randpic_ios5":"","randpic_desc":"孙燕姿","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474182530e469d3d46be1441266f3b4d94f303d60.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147418253336fecbca703ec4ead0a66b098ce8e530.jpg","randpic_2":"bos_client_1474182527a9f8af26c8d00972bd8eba139bbd2233","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474182527a9f8af26c8d00972bd8eba139bbd2233.jpg","special_type":0,"ipad_desc":"孙燕姿","is_publish":"111101","mo_type":"2","type":2,"code":"271553878"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474156710833250e087999209c2c6425a1001028a.jpg","randpic_ios5":"","randpic_desc":"冯建宇","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474156725bb51c8de3e28e0796ddf9cc5ba8640a6.jpg","randpic_qq":"","randpic_2":"bos_client_1474156717725d88de8728aecbfd04586f7da42b2f","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1474156717725d88de8728aecbfd04586f7da42b2f.jpg","special_type":0,"ipad_desc":"冯建宇","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/bigwig/v10/index.html"}]
     * error_code : 22000
     */

    private int error_code;
    /**
     * randpic : http://business.cdn.qianqian.com/qianqian/pic/bos_client_14743544044acbfec7623a1341ab583a93adeca8a4.jpg
     * randpic_ios5 :
     * randpic_desc : 果酱开放日
     * randpic_ipad :
     * randpic_qq :
     * randpic_2 : bos_client_147435440887f7ed5fbf2bb5df2eeff493025425de
     * randpic_iphone6 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_147435440887f7ed5fbf2bb5df2eeff493025425de.jpg
     * special_type : 0
     * ipad_desc : 果酱开放日
     * is_publish : 111100
     * mo_type : 5
     * type : 7
     * code : 354504733
     */

    private List<PicBean> pic;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public static class PicBean {
        private String randpic;
        private String randpic_ios5;
        private String randpic_desc;
        private String randpic_ipad;
        private String randpic_qq;
        private String randpic_2;
        private String randpic_iphone6;
        private int special_type;
        private String ipad_desc;
        private String is_publish;
        private String mo_type;
        private int type;
        private String code;

        public String getRandpic() {
            return randpic;
        }

        public void setRandpic(String randpic) {
            this.randpic = randpic;
        }

        public String getRandpic_ios5() {
            return randpic_ios5;
        }

        public void setRandpic_ios5(String randpic_ios5) {
            this.randpic_ios5 = randpic_ios5;
        }

        public String getRandpic_desc() {
            return randpic_desc;
        }

        public void setRandpic_desc(String randpic_desc) {
            this.randpic_desc = randpic_desc;
        }

        public String getRandpic_ipad() {
            return randpic_ipad;
        }

        public void setRandpic_ipad(String randpic_ipad) {
            this.randpic_ipad = randpic_ipad;
        }

        public String getRandpic_qq() {
            return randpic_qq;
        }

        public void setRandpic_qq(String randpic_qq) {
            this.randpic_qq = randpic_qq;
        }

        public String getRandpic_2() {
            return randpic_2;
        }

        public void setRandpic_2(String randpic_2) {
            this.randpic_2 = randpic_2;
        }

        public String getRandpic_iphone6() {
            return randpic_iphone6;
        }

        public void setRandpic_iphone6(String randpic_iphone6) {
            this.randpic_iphone6 = randpic_iphone6;
        }

        public int getSpecial_type() {
            return special_type;
        }

        public void setSpecial_type(int special_type) {
            this.special_type = special_type;
        }

        public String getIpad_desc() {
            return ipad_desc;
        }

        public void setIpad_desc(String ipad_desc) {
            this.ipad_desc = ipad_desc;
        }

        public String getIs_publish() {
            return is_publish;
        }

        public void setIs_publish(String is_publish) {
            this.is_publish = is_publish;
        }

        public String getMo_type() {
            return mo_type;
        }

        public void setMo_type(String mo_type) {
            this.mo_type = mo_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
