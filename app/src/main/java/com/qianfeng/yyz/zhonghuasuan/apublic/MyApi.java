package com.qianfeng.yyz.zhonghuasuan.apublic;

/**
 * Created by Administrator on 2016/10/15 0015.
 */
public class MyApi {

    public static final String UID = "uid";
    public static final String VERSION = "version";
    public static final String CLIENT_TYPE = "client_type";
    public static final String BASE_URL = "http://appshow.zhonghuasuan.com";
    public static final String TYPE = "type";
    public static final String SIZE = "size";
    public static final String PAGE = "page";
    public static final String CATEGORY_ID = "category_id";
    public static final String SORT = "sort";
    public static final String GENERAL_FPATH = "zhs";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String GID = "gid";
    public static final String KEYWORD = "keyword";
    public static final String SEARCH_KEY = "search_key";
    public static final String PASSWORD ="password";
    public static final String ACCOUNT = "account";
    public static final String U_NAME = "uname";
    public static final String IS_PREMIUM = "is_premium";
    public static final String AVATAR = "avatar";
    public static final String SIGN = "sign";

    public static class Home{

        public static final String GENERAL_DATA = "/other/general_data";
        public static final String APP_INDEX = "/home/app_index";
        public static final String APP_GUSS_LIKE = "/home/guess_like_goods";
        public static final String New_NOTICE = "/home/foreshow_goods";
        public static final String EIGHTFILEDS = "/goods/goods_list";
        public static final String CLASSIFY_SECOND = "/goods/goods_list";
        public static final String ZDB = "http://www.zhongduobao.com/";
    }

    public static final String DETAILS = "/goods/detail";

    public class Search{
        public static final String KER_WORD = "/search/hot_keyword";
        public static final String SEARCHING = "/search/relevant_search";
    }


    public static class Mine{
        public static final String LOGIN = "http://loginapp.zhonghuasuan.com/home/app_login";
        public static final String UPDATAIMG = "http://appbusiness.zhonghuasuan.com/user_avatar/upload_avatar";
    }
}
