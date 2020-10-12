package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


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
import java.util.ArrayList;
import java.util.List;

public class RateShow extends AppCompatActivity implements Runnable{

    private static final String TAG = "RateShow";

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            ListView listview = findViewById(R.id.mylist);
            if(msg.what==5){
                //Log.i(TAG,"handlerMessage:getMessage msg = ");
                String str = (String)msg.obj;
                //Log.i(TAG,"handlerMessage:getMessage msg = "+str);
                Document doc = Jsoup.parse(str);
                Elements tables = doc.getElementsByTag("table");
                Element table = tables.get(0);
                Elements tds = table.getElementsByTag("td");
                float v;
                ArrayList<String> list = new ArrayList<String>();
                for(int i=0;i<tds.size();i+=6){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+5);
                    String str1 = td1.text();
                    String val = td2.text();
                    Log.i(TAG,"run: " + str1 + "==>" + val);
                    v = 100f / Float.parseFloat(val);
                    list.add((str1 + "==>" + v));
                }
                ListAdapter adapter = new ArrayAdapter<String>(RateShow.this,android.R.layout.simple_list_item_1,list);
                listview.setAdapter(adapter);
            }super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_show);

        Thread t1 = new Thread(this);
        t1.start();
    }

    @Override
    public void run() {
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