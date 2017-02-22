package edu.hzuapps.andriodworks.homeworks.net1314080903204.tcp_tester;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Create By ChenHauyu
 * Email：mrchenhy@gmail.com
 */

/* 客户端界面代码 */
public class Net1314080903204_ClientActivity extends Activity {
    private final String TAG="Net1314080903204_ClientActivity";
    private EditText edit_ip;
    private EditText edit_port;
    private EditText edit_send;
    private EditText edit_recv;
    private Button btn_connect;
    private Button btn_send;
    private boolean isConnected = false;
    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;
    private String line;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net1314080903204_activity_client);
        /* 初始化 */
        edit_ip = (EditText) findViewById(R.id.edit_ip);
        edit_port = (EditText) findViewById(R.id.edit_port);
        edit_send = (EditText) findViewById((R.id.edit_msgsend));
        edit_recv = (EditText) findViewById(R.id.edit_recv);
        btn_connect = (Button)findViewById(R.id.btn_connect);
        btn_send = (Button)findViewById(R.id.btn_send);
        /* 连接按钮 */
        btn_connect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /* 连接按钮处理函数 */
                connect();
            }
        });
        /* 发送按钮 */
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 发送按钮处理函数 */
                send();
            }
        });
    }

    /* 定义Handler对象 */
    private Handler handler = new Handler() {
        @Override
        /* 当有消息发送出来的时候就执行Handler的这个方法 */
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /* 更新UI */
            edit_recv.append(line);
            /* 调试输出 */
            Log.i("PDA", "----->" + line);
        }
    };

    /* 连接按钮处理函数：建立Socket连接 */
    @SuppressLint("HandlerLeak")
    public void connect() {
        if(false == isConnected){
            new Thread() {
                public void run(){
                    String IPAdr = edit_ip.getText().toString();
                    int PORT = Integer.parseInt(edit_port.getText().toString());
                    try {
                        /* 建立socket */
                        socket = new Socket(IPAdr, PORT);
                        /* 输出流 */
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        /* 输入流 */
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        /* 调试输出 */
                        Log.i(TAG, "输入输出流获取成功");
                        Log.i(TAG, "检测数据");
                        /* 读数据并更新UI */
                        char[] buf = new char[2048];
                        int i;
                        while((i= reader.read(buf,0,100))!=-1)
                        {
                            line = new String(buf,0,i);
                            Message msg = handler.obtainMessage();
                            msg.obj = line;
                            handler.sendMessage(msg);
                            Log.i(TAG, "send to handler");
                        }
                    } catch (UnknownHostException e){
                        Toast.makeText(Net1314080903204_ClientActivity.this,"无法建立连接：）",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        isConnected = false;
                    }
                    catch (IOException e) {
                        Toast.makeText(Net1314080903204_ClientActivity.this,"无法建立连接：）",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        isConnected = false;
                    }
                }
            }.start();
            isConnected = true;
            /* 更新UI */
            btn_connect.setText("断开");
            Toast.makeText(Net1314080903204_ClientActivity.this,"连接成功：）",Toast.LENGTH_SHORT).show();
        }else{
            isConnected = false;
            /* 更新UI */
            btn_connect.setText("连接");
            edit_send.setText("");
            edit_recv.setText("");
            /* 关闭socket */
            onDestroy();
            Toast.makeText(Net1314080903204_ClientActivity.this,"连接已断开：）",Toast.LENGTH_SHORT).show();
        }
    }

    /* 发送按钮处理函数：向输出流写数据 */
    public void send() {
        try {
            /* 向输出流写数据 */
            writer.write(edit_send.getText().toString()+"\n");
            writer.flush();
            /* 更新UI */
            edit_send.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            /* 关闭socket */
            if(null != socket){
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.getInputStream().close();
                socket.getOutputStream().close();
                socket.close();
            }
        } catch (IOException e) {
            Log.d(TAG,e.getMessage());
        }
        /* 更新UI */
        btn_connect.setText("连接");
        edit_recv.setText("");
        super.onDestroy();
    }
}
