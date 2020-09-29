package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Hdler extends AppCompatActivity implements Runnable {

    private static final String TAG = "Handler";

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==5){
                //Log.i(TAG,"handlerMessage:getMessage msg = ");
                String str = (String)msg.obj;
                //Log.i(TAG,"handlerMessage:getMessage msg = "+str);
                TextView show = findViewById(R.id.textView12);
                Document doc = Jsoup.parse(str);
                Elements tables = doc.getElementsByTag("table");
                Element table = tables.get(0);
                Elements tds = table.getElementsByTag("td");
                float v;
                Map<String, Float> rate = new HashMap<String, Float>();
                for(int i=0;i<tds.size();i+=6){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+5);

                    String str1 = td1.text();
                    String val = td2.text();
                    //Log.i(TAG,"run: " + str1 + "==>" + val);
                    v = 100f / Float.parseFloat(val);
                    rate.put(str1,v);
                    Log.i(TAG,"run: " + str1 + "==>" + v);
                }
                Log.i(TAG, "美元汇率： "+ rate.get("美元"));
                Log.i(TAG, "欧元汇率： "+ rate.get("欧元"));
                Log.i(TAG, "韩元汇率： "+ rate.get("韩元"));
                show.setText(str);
            }super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        Thread t = new Thread(this);
        t.start();

    }


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
        Reader in = new InputStreamReader(inputStream, "GBK");
        while (true) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}