package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Hdler extends AppCompatActivity implements Runnable {

    private static final String TAG = "Handler";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        final TextView show = findViewById(R.id.textView12);
        //开启线程
        Thread t = new Thread(this);
        t.start();
        handler = new Handler(){
            public void handlerMessage(Message msg){
                if(msg.what==5){
                    String str = (String)msg.obj;
                    Log.i(TAG,"handlerMessage:getMessage msg = "+str);
                    show.setText(str);
                }
            }
        };

    }

    //通过Handler实现线程间通信
    @Override
    public void run() {
        /*Log.i(TAG,"run:run()...");
        Message msg = handler.obtainMessage(5);
        //msg.what = 5;
        msg.obj = "hello from run()";
        handler.sendMessage(msg);*/
        URL url = null;
        try {
            url = new URL("www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
            Log.i(TAG,"run:html"+html);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException {

    }
}