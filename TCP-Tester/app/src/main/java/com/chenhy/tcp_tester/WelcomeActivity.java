package com.chenhy.tcp_tester;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

/**
 * Create By HauyuChen
 * Email：Hauyu.Chen@gmail.com
 */

/* 欢迎界面代码*/
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toselect = new Intent(WelcomeActivity.this,SelectActivity.class);    //跳转至功能选择界面
                startActivity(toselect);
                WelcomeActivity.this.finish();
                finish();
            }
        },3000);    //延时跳转：3秒
    }
}

