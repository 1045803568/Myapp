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
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyClass {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(" HHmmss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String nowdate = simpleDateFormat.format(date);
        long longdate = Long.parseLong(nowdate);
        String nowtime = simpleTimeFormat.format(date);
        long longtime = Long.parseLong(nowtime);
        System.out.println(longdate);

        try {
            Date nd = simpleDateFormat.parse(nowdate);
            long n1 = nd.getTime();

            Date nt = simpleTimeFormat.parse(nowtime);
            //Date di = simpleTimeFormat.parse(dateindex);
            //System.out.println(di);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}