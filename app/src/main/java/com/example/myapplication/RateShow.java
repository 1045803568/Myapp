package com.example.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateShow extends AppCompatActivity implements Runnable{

    private static final String TAG = "RateShow";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(" HH:mm:ss");// HH:mm:ss
    //获取当前时间
    Date date = new Date(System.currentTimeMillis());
    long d1 = date.getTime()
    String nowdate = simpleDateFormat.format(date);
    String nowtime = simpleTimeFormat.format(date);
    String dateindex = "08:00:00";

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==5){
                //Log.i(TAG,"handlerMessage:getMessage msg = ");
                String str = (String)msg.obj;
                //Log.i(TAG,"handlerMessage:getMessage msg = "+str);
                Document doc = Jsoup.parse(str);
                Elements tables = doc.getElementsByTag("table");
                Element table = tables.get(0);
                Elements tds = table.getElementsByTag("td");
                SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("date",nowdate);
                editor.putString("time",nowtime);
                ListView listview = findViewById(R.id.mylist);
                ArrayList<String> list = new ArrayList<String>();
                float v;
                for(int i=0;i<tds.size();i+=6){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+5);
                    String str1 = td1.text();
                    String val = td2.text();
                    Log.i(TAG,"run: " + str1 + "==>" + val);
                    v = 100f / Float.parseFloat(val);
                    list.add(str1 + "==>" + val);
                    editor.putFloat(str1,v);
                }
                editor.putInt("size",list.size());
                editor.apply();
                finish();
                ListAdapter adapter = new ArrayAdapter<String>(RateShow.this,android.R.layout.simple_list_item_1,list);
                listview.setAdapter(adapter);
            }super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_show);
        SharedPreferences sharedPreferences= getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String savetime = sharedPreferences.getString("time",nowtime);
        String savedate = sharedPreferences.getString("date",nowtime);
        try {
            Date st = simpleTimeFormat.parse(savetime);
            Date sd = simpleDateFormat.parse(savedate);
            Date nd = simpleDateFormat.parse(nowdate);
            Date nt = simpleDateFormat.parse(nowtime);
            Date di = simpleDateFormat.parse(dateindex);
            if(nowdate.equals(savedate)&&nt.getTime()>di.getTime()){
                Thread t1 = new Thread(this);
                t1.start();
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                for(int i =0;i<sharedPreferences.l)
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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