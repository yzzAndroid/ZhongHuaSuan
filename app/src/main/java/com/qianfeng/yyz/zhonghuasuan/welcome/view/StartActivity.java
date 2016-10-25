package com.qianfeng.yyz.zhonghuasuan.welcome.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.qianfeng.yyz.zhonghuasuan.R;
import com.qianfeng.yyz.zhonghuasuan.home.view.HomeActivity;
import com.qianfeng.yyz.zhonghuasuan.welcome.presenter.StartPresenterImpl;

public class StartActivity extends AppCompatActivity implements IStartView{

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resetWindowSize();
        setContentView(R.layout.activity_start);
        //初始化
        init();
        //激活
        StartPresenterImpl.getInstance().start(this,this);

    }

    @Override
    public void init() {
        //暂时还没有
        handler = new Handler();
    }

    @Override
    public void firstUse() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        },300);

    }



    @Override
    public void resetWindowSize() {
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
    }

    @Override
    public void secondUse() {
        //启动主页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },300);
    }
}
