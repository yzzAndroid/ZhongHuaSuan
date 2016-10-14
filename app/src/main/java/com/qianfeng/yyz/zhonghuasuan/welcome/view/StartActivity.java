package com.qianfeng.yyz.zhonghuasuan.welcome.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.yyz.zhonghuasuan.R;

public class StartActivity extends AppCompatActivity implements IStartView{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    @Override
    public void init() {
        //暂时还没有

    }

    @Override
    public void firstUse() {
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void resetWindowSize() {

    }
}
