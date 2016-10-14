package com.qianfeng.yyz.zhonghuasuan.welcome.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianfeng.yyz.zhonghuasuan.R;

public class WelcomeActivity extends AppCompatActivity implements IwelcomeView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void startUse() {

    }

    @Override
    public void resetWindowSize() {

    }
}
