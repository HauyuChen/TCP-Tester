package edu.hzuapps.andriodworks.homeworks.net1314080903204.tcp_tester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Create By ChenHauyu
 * Email：mrchenhy@gmail.com
 */

/*功能选择界面代码*/
public class Net1314080903204_SelectActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net1314080903204_activity_select);
        /*“模拟服务端”按钮事件*/
        findViewById(R.id.btn_toServer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Net1314080903204_SelectActivity.this,Net1314080903204_ServerActivity.class);   //页面跳转至服务端界面
                startActivity(intent);
            }
        });
        /*“模拟客户端”按钮事件*/
        findViewById(R.id.btn_toClient).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Net1314080903204_SelectActivity.this,Net1314080903204_ClientActivity.class);   //页面跳转至客户端界面
                startActivity(intent);
            }
        });
    }
}

