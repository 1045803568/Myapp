//package com.example.myapplication;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ListActivity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Handler;
//import android.os.Message;
//import android.preference.PreferenceManager;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//
//public class RateShow extends ListActivity implements Runnable,AdapterView.OnItemClickListener {
//
//    private static final String TAG = "RateShow";
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
//    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HHmmss");// HH:mm:ss
//    Date date = new Date(System.currentTimeMillis());
//    String nowdate = simpleDateFormat.format(date);
//    long longnowdate = Long.parseLong(nowdate);
//    String nowtime = simpleTimeFormat.format(date);
//    long longnowtime = Long.parseLong(nowtime);
//    String dateindex = "080000";
//    long longdateindex = Long.parseLong(dateindex);
//    private SimpleAdapter listItemAdapter;
////    ListView listView1 = findViewById(R.id.mylist);
//    ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
//    SimpleAdapter adapter = new SimpleAdapter(this,list1,android.R.layout.activity_list_item,new String[] { "ItemTitle", "ItemDetail" },
//            new int[] { R.id.currency, R.id.rate});
//
//    Handler handler = new Handler(){
//        public void handleMessage(Message msg){
//            if(msg.what==5){
//                //Log.i(TAG,"handlerMessage:getMessage msg = ");
//                String str = (String)msg.obj;
//                //Log.i(TAG,"handlerMessage:getMessage msg = "+str);
//                Document doc = Jsoup.parse(str);
//                Elements tables = doc.getElementsByTag("table");
//                Element table = tables.get(0);
//                Elements tds = table.getElementsByTag("td");
//                SharedPreferences sharedPreferences = getSharedPreferences("rate", Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putLong("date",longnowdate);
//                editor.putLong("time",longnowtime);
////                ListView listview = findViewById(R.id.mylist);
//                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//                float v;
//
//                for(int i=0;i<tds.size();i+=6){
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    Element td1 = tds.get(i);
//                    Element td2 = tds.get(i+5);
//                    String str1 = td1.text();
//                    String val = td2.text();
//                    Log.i(TAG,"run: " + str1 + "==>" + val);
//                    v = 100f / Float.parseFloat(val);
//                    map.put("currency",str1);
//                    map.put("rate",String.valueOf(v));
//                    list.add(map);
//                    editor.putString("rate"+i,str1 + "==>" + v);
//                }
//                editor.putInt("size",list.size());
//                editor.putBoolean("is_first_open",false);
//                editor.apply();
//                finish();
//                SimpleAdapter adapter1 = new SimpleAdapter(this,list1,android.R.layout.activity_list_item,new String[] { "ItemTitle", "ItemDetail" },
//                        new int[] { R.id.currency, R.id.rate});
////                listview.setAdapter(adapter1);
////                adapter1.setEmptyView(findViewById(R.id.textView12));
//            }super.handleMessage(msg);
//        }
//    };
//
//    private void initListView() {
//        list1 = new ArrayList<HashMap<String, String>>();
//        for (int i = 0; i < 10; i++) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("currency", "Rate： " + i); // 标题文字
//            map.put("rate", "detail" + i); // 详情描述
//            list1.add(map);
//        }
//        // 生成适配器的Item和动态数组对应的元素
//        listItemAdapter = new SimpleAdapter(this, list1, // listItems数据源
//                R.layout.activity_rate_show, // ListItem的XML布局实现
//                new String[] { "currency", "rate" },
//                new int[] { R.id.currency, R.id.rate }
//        );
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rate_show);
//
//
//        SharedPreferences sharedPreferences= getSharedPreferences("rate", Activity.MODE_PRIVATE);
//        //PreferenceManager.getDefaultSharedPreferences(this);
//
//        boolean isFirstIn = sharedPreferences.getBoolean("is_first_open", true);
//
//        Log.i(TAG, "onCreate: "+sharedPreferences.getInt("size",0)*6);
//        if(isFirstIn){
//            Thread t1 = new Thread(this);
//            t1.start();
//        }else {
//            long savedate = sharedPreferences.getLong("date",longnowdate);
//            if(longnowdate!=savedate&&longnowtime>longdateindex){
//                Thread t1 = new Thread(this);
//                t1.start();
//            }
//            else {
//                int j;
//                for(j = 0;j<sharedPreferences.getInt("size",0)*6;j+=6){
//                    list1.add(sharedPreferences.getString("rate"+j,null));
//                   // Log.i(TAG, "onCreate: "+sharedPreferences.getString("rate"+j,"1"));
//                }
//                listView1.setAdapter(adapter1);
//                listView1.setEmptyView(findViewById(R.id.textView12));
//                listView1.setOnItemClickListener(this);
////                listView1.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) this);
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        URL url = null;
//        try {
//            url = new URL("https://www.usd-cny.com/bankofchina.htm");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//
//            String html = inputStream2String(in);
//            Message msg = handler.obtainMessage(5,html);
//            handler.sendMessage(msg);
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    private String inputStream2String(InputStream inputStream) throws IOException {
//
//        final int bufferSize = 1024;
//        final char[] buffer = new char[bufferSize];
//        final StringBuilder out = new StringBuilder();
//        Reader in = new InputStreamReader(inputStream, "GBK");
//        while (true) {
//            int rsz = in.read(buffer, 0, buffer.length);
//            if (rsz < 0)
//                break;
//            out.append(buffer, 0, rsz);
//        }
//        return out.toString();
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////                Log.i(TAG, "onClick: 对话框事件处理");
////
////            }
////        });
//        Intent intent = new Intent();
//
//        Object itemAtPosition = listView1.getItemAtPosition(position);
//        ArrayList<String> list2 = (ArrayList<String>)itemAtPosition;
//
//        intent.putExtra("currency",((ArrayList<String>) itemAtPosition).get())
//
//
//}