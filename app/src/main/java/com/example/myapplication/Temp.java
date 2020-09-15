package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Temp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        //按钮点击事件
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            //获取华氏度
            EditText input = findViewById(R.id.editTextTextPersonName2);
            int inputText = Integer.valueOf(input.getText().toString());

            //显示摄氏度
            TextView output = findViewById(R.id.textView3);
            output.setText((int) ((inputText-32)/1.8));
        }
    }
}