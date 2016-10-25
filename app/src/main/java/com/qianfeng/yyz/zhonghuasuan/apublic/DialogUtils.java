package com.qianfeng.yyz.zhonghuasuan.apublic;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.qianfeng.yyz.zhonghuasuan.R;

import java.net.URL;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class DialogUtils {


    public static  AlertDialog beginLoading(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.loading_dialog_item,null);
        WebView webView = (WebView) linearLayout.findViewById(R.id.dialog_web_view);
        webView.loadDataWithBaseURL(null,"<HTML><body bgcolor='#f3f3f3'><div align=center><IMG src='file:///android_mipmap/zhs_loading.gif'/></div></body></html>", "text/html", "UTF-8",null);
        builder.setView(linearLayout);
        AlertDialog dialog = builder.create();
        return dialog;
    }


}
