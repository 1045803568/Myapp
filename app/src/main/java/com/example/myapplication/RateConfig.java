package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RateConfig extends AppCompatActivity {

    private static final String TAG = "Rate_config";
    EditText d1;
    EditText e1;
    EditText w1;
    float dollar,euro,won;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_config);

        d1 = findViewById(R.id.editTextTextPersonName4);
        e1 = findViewById(R.id.editTextTextPersonName5);
        w1 = findViewById(R.id.editTextTextPersonName6);

        //从RateExchange 传来的数据
        /*Intent itent = getIntent();
        dollar = itent.getFloatExtra("dollar_rate",0.0000f);
        euro = itent.getFloatExtra("euro_rate",0.0000f);
        won = itent.getFloatExtra("won_rate",0.0000f);*/

        //从myrate传来的数据
        SharedPreferences sp =getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        dollar = sp.getFloat("dallar_rate",0.000f);
        euro = sp.getFloat("euro_rate",0.000f);
        won = sp.getFloat("won_rate",0.000f);

        d1.setText(String.valueOf(dollar));
        e1.setText(String.valueOf(euro));
        w1.setText(String.valueOf(won));

        Log.i(TAG,"openOne:dallarRate"+dollar);
        Log.i(TAG,"openOne:euroRate"+euro);
        Log.i(TAG,"openOne:wonRate"+won);
    }

    public void save(View view){
        dollar = Float.parseFloat(d1.getText().toString());
        euro = Float.parseFloat(e1.getText().toString());
        won = Float.parseFloat(w1.getText().toString());

        //Intent返回参数
        /*Intent intent2 = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("dollar_rate2",dollar);
        bdl.putFloat("euro_rate2",euro);
        bdl.putFloat("won_rate2",won);
        intent2.putExtras(bdl);
        setResult(2,intent2);*/

        //修改myrate值

        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat("dollar_rate",dollar);
        editor.putFloat("euro_rate",euro);
        editor.putFloat("won_rate",won);
        editor.apply();
        finish();
    }
}