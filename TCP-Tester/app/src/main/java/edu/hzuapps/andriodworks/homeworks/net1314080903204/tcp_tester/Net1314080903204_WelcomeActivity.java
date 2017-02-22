package edu.hzuapps.andriodworks.homeworks.net1314080903204.tcp_tester;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

/**
 * Create By ChenHauyu
 * Email：mrchenhy@gmail.com
 */

/* 欢迎界面代码*/
public class Net1314080903204_WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net1314080903204_activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toselect = new Intent(Net1314080903204_WelcomeActivity.this,Net1314080903204_SelectActivity.class);    //跳转至功能选择界面
                startActivity(toselect);
                Net1314080903204_WelcomeActivity.this.finish();
                finish();
            }
        },3000);    //延时跳转：3秒
    }
}

