package com.qianfeng.yyz.zhonghuasuan.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

@Entity(nameInDb = "native_goods")
public class MyNativeInfo {

    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String img;
    @NotNull
    private String title;
    @NotNull
    private String priceCost;
    @NotNull
    private String priceBack;
    @NotNull
    private String price;
    @NotNull
    private String nowNum;
    @NotNull
    private String num;
    @NotNull
    private String gid;
    @Generated(hash = 1261696596)
    public MyNativeInfo(Long id, @NotNull String img, @NotNull String title,
            @NotNull String priceCost, @NotNull String priceBack,
            @NotNull String price, @NotNull String nowNum, @NotNull String num,
            @NotNull String gid) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.priceCost = priceCost;
        this.priceBack = priceBack;
        this.price = price;
        this.nowNum = nowNum;
        this.num = num;
        this.gid = gid;
    }
    @Generated(hash = 1142696225)
    public MyNativeInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPriceCost() {
        return this.priceCost;
    }
    public void setPriceCost(String priceCost) {
        this.priceCost = priceCost;
    }
    public String getPriceBack() {
        return this.priceBack;
    }
    public void setPriceBack(String priceBack) {
        this.priceBack = priceBack;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getNowNum() {
        return this.nowNum;
    }
    public void setNowNum(String nowNum) {
        this.nowNum = nowNum;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getGid() {
        return this.gid;
    }
    public void setGid(String gid) {
        this.gid = gid;
    }
}
