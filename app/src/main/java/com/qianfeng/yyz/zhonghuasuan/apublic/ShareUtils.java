package com.qianfeng.yyz.zhonghuasuan.apublic;

import android.content.Context;
import android.content.res.AssetManager;

import com.qianfeng.yyz.zhonghuasuan.bean.GoodsDetail;

import java.io.InputStream;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class ShareUtils {

    public static void showShare(Context context, GoodsDetail goodsDetail) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

       // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(goodsDetail.getData().getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(goodsDetail.getData().getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(goodsDetail.getData().getTitle());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(goodsDetail.getData().getImgs().get(0).getImg());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(goodsDetail.getData().getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(goodsDetail.getData().getTitle());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("yzzShare");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(goodsDetail.getData().getUrl());

// 启动分享GUI
        oks.show(context);
    }

}
