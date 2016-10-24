package com.qianfeng.yyz.zhonghuasuan.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class HotKeyWord {

    /**
     * code : 201
     * msg : 请求成功
     * data : [{"keyword":"连衣裙"},{"keyword":"太阳镜"},{"keyword":"蚊帐"},{"keyword":"雨伞"},{"keyword":"水杯"},{"keyword":"儿童玩具"},{"keyword":"凉鞋"},{"keyword":"面膜"},{"keyword":"双肩包女"},{"keyword":"零食"}]
     */

    private int code;
    private String msg;
    /**
     * keyword : 连衣裙
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String keyword;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
