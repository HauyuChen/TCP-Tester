package com.chenhy.tcp_tester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Create By HauyuChen
 * Email：Hauyu.Chen@gmail.com
 */

/*功能选择界面代码*/
public class SelectActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        /*“模拟服务端”按钮事件*/
        findViewById(R.id.btn_toServer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this,ServerActivity.class);   //页面跳转至服务端界面
                startActivity(intent);
            }
        });
        /*“模拟客户端”按钮事件*/
        findViewById(R.id.btn_toClient).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this,ClientActivity.class);   //页面跳转至客户端界面
                startActivity(intent);
            }
        });
    }
}

