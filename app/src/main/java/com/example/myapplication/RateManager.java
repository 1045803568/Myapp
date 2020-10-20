package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class RateManager {
    private  CurDbHelper curDbHelper;
    private String TBNAME;

    public RateManager(Context context){
        curDbHelper = new CurDbHelper(context);
        TBNAME = CurDbHelper.TB_NAME;
    }

    public void add(RateItem item){
        SQLiteDatabase db = curDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("curname",item.getCurname());
        values.put("currate",item.getCurrate());

        db.insert(TBNAME,null,values);
        db.close();
    }

    public RateItem findByID(int id){
        SQLiteDatabase db = curDbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,"ID=?",new String[]{String.valueOf(id)},null,null,null);

        RateItem item = null;
        if(cursor!=null && cursor.moveToFirst()){
            item = new RateItem();
            item.setID(cursor.getInt((cursor.getColumnIndex("ID"))));
            item.setCurname(cursor.getString((cursor.getColumnIndex("CURNAME"))));
            item.setCurrate(cursor.getString((cursor.getColumnIndex("CURRATE"))));
            cursor.close();
        }
        return item;
    }

    public List<RateItem> listAll(){
        List<RateItem> ratelist = null;
        SQLiteDatabase db = curDbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME,null,null,null,null,null,null);
        if(cursor!=null){
            ratelist = new ArrayList<RateItem>();
            while (cursor.moveToNext()){
                RateItem item = new RateItem();
                item.setID(cursor.getInt((cursor.getColumnIndex("ID"))));
                item.setCurname(cursor.getString((cursor.getColumnIndex("CURNAME"))));
                item.setCurrate(cursor.getString((cursor.getColumnIndex("CURRATE"))));

                ratelist.add(item);
            }
            cursor.close();
        }
        db.close();
        return ratelist;
    }
}

