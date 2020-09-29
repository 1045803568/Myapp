package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Hdler extends AppCompatActivity implements Runnable {

    private static final String TAG = "Handler";

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==5){
                Log.i(TAG,"handlerMessage:getMessage msg = ");
                String str = (String)msg.obj;
                Log.i(TAG,"handlerMessage:getMessage msg = "+str);
                TextView show = findViewById(R.id.textView12);
                show.setText(str);
            }super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        //开启线程
        Thread t = new Thread(this);
        t.start();

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
            url = new URL("https://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
            Message msg = handler.obtainMessage(5,html);
            handler.sendMessage(msg);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "gb2312");
        while (true) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}