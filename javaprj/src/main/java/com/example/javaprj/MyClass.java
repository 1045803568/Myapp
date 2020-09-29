package com.example.javaprj;

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

public class MyClass {
    public static void main(String[] args) {
        String html = null;
        URL url = null;
        try {
            url = new URL("https://www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            html = inputStream2String(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc = Jsoup.parse(html);
        Elements tables = doc.getElementsByTag("table");
        Element table = tables.get(0);
        Elements tds = table.getElementsByTag("td");
        float v;
        for(int i=0;i<tds.size();i+=6){
            Element td1 = tds.get(i);
            Element td2 = tds.get(i+5);

            String str1 = td1.text();
            String val = td2.text();
            //Log.i(TAG,"run: " + str1 + "==>" + val);
            v = 100f / Float.parseFloat(val);
            //Log.i(TAG,"run: " + str1 + "==>" + v);
            System.out.println("run: " + str1 + "==>" + v);
        }
    }
    private static String inputStream2String(InputStream inputStream) throws IOException {
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