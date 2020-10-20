package com.example.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CurRateDb extends AppCompatActivity implements Runnable {

    private static final String TAG = "CurRateDb";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HHmmss");// HH:mm:ss
    Date date = new Date(System.currentTimeMillis());
    String nowdate = simpleDateFormat.format(date);
    long longnowdate = Long.parseLong(nowdate);
    String nowtime = simpleTimeFormat.format(date);
    long longnowtime = Long.parseLong(nowtime);
    String dateindex = "080000";
    long longdateindex = Long.parseLong(dateindex);
    ListView listView = findViewById(R.id.ratelist);
    RateManager rm = new RateManager(this);


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
                SharedPreferences sharedPreferences = getSharedPreferences("ratedb", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("date",longnowdate);
                editor.putLong("time",longnowtime);
                RateItem it = new RateItem();
                ArrayList<String> list = new ArrayList<>();
                float v;
                for(int i=0;i<tds.size();i+=6){
                    Element td1 = tds.get(i);
                    Element td2 = tds.get(i+5);
                    String str1 = td1.text();
                    String val = td2.text();
                    Log.i(TAG,"run: " + str1 + "==>" + val);
                    v = 100f / Float.parseFloat(val);
                    it.setCurname(str1);
                    it.setCurrate(String.valueOf(v));
                    rm.add(it);
                    list.add("Currency: "+str1+" ==> "+v);
                }
                editor.apply();
                finish();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CurRateDb.this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(adapter);
            }super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cur_rate_db);

        SharedPreferences sharedPreferences= getSharedPreferences("ratedb", Activity.MODE_PRIVATE);
        //PreferenceManager.getDefaultSharedPreferences(this);

        boolean isFirstIn = sharedPreferences.getBoolean("is_first_open", true);

        if(isFirstIn){
            Thread t1 = new Thread(this);
            t1.start();
        }else {
            long savedate = sharedPreferences.getLong("date",longnowdate);
            if(longnowdate!=savedate&&longnowtime>longdateindex){
                Thread t1 = new Thread(this);
                t1.start();
            }
            else {
                RateManager rm = new RateManager(this);

                listView1.setAdapter(adapter1);
                listView1.setEmptyView(findViewById(R.id.textView12));
                listView1.setOnItemClickListener(this);
//                listView1.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) this);
            }
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