package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateExchange extends AppCompatActivity{

    private TextView output;
    private EditText input;

    float dollarRate = 0.1474f;
    float euroRate = 0.1225f;
    float wonRate = 171.3606f;
    private static final String TAG = "RateExchange";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_exchange);

        input = findViewById(R.id.editTextTextPersonName3);
        output = findViewById(R.id.textView10);
        Button b1 = findViewById(R.id.button9);
        Button b2 = findViewById(R.id.button13);
        Button b3 = findViewById(R.id.button15);


    }


    public void exchange(View bt){
        if(input.getText().toString().length()==0){
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }else {
            switch(bt.getId()) {
                case R.id.button9:
                    float f1 = Float.parseFloat(input.getText().toString());
                    output.setText(String.valueOf((f1*dollarRate)));
                    break;
                case R.id.button13:
                    float f2 = Float.parseFloat(input.getText().toString());
                    output.setText(String.valueOf((f2*euroRate)));
                    break;
                case R.id.button15:
                    float f3 = Float.parseFloat(input.getText().toString());
                    output.setText(String.valueOf((f3*wonRate)));
                    break;
            }
        }
    }


    //打开带返回值的窗口
    public void open(View v) {
        Intent config = new Intent(this, RateConfig.class);
        config.putExtra("dollar_rate",dollarRate);
        config.putExtra("euro_rate",euroRate);
        config.putExtra("won_rate",wonRate);

        Log.i(TAG,"openOne:dollarRate"+dollarRate);
        Log.i(TAG,"openOne:euroRate"+euroRate);
        Log.i(TAG,"openOne:wonRate"+wonRate);

        /*startActivity(config);*/
        startActivityForResult(config,1);
    }

    //通过保存文件的形式传递数据
    public void set(View v){
        Intent config = new Intent(this, RateConfig.class);
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat("dallar_rate",dollarRate);
        editor.putFloat("euro_rate",euroRate);
        editor.putFloat("won_rate",wonRate);
        editor.apply();
        startActivity(config);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==2){
            Bundle bd = data.getExtras();
            dollarRate = bd.getFloat("dollar_rate2",0.0000f);
            euroRate = bd.getFloat("euro_rate2",0.0000f);
            wonRate = bd.getFloat("won_rate2",0.0000f);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    public boolean openContextMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


}