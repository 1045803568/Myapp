package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Counter2 extends AppCompatActivity {

    TextView count1,count2;
    int sum1,sum2 = 0;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter2);
        count1 = findViewById(R.id.textView6);
        count2 = findViewById(R.id.textView8);

        bt1 = findViewById(R.id.button6);
        bt2 = findViewById(R.id.button7);
        bt3 = findViewById(R.id.button8);
        bt4 = findViewById(R.id.button10);
        bt5 = findViewById(R.id.button11);
        bt6 = findViewById(R.id.button12);
        bt7 = findViewById(R.id.button14);

        bt1.setOnClickListener(new Counter2.BtnClickListener());
        bt2.setOnClickListener(new Counter2.BtnClickListener());
        bt3.setOnClickListener(new Counter2.BtnClickListener());
        bt4.setOnClickListener(new Counter2.BtnClickListener());
        bt5.setOnClickListener(new Counter2.BtnClickListener());
        bt6.setOnClickListener(new Counter2.BtnClickListener());
        bt7.setOnClickListener(new Counter2.BtnClickListener());

    }
    class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button6:
                    sum1 = sum1 + 3;
                    count1.setText(String.valueOf(sum1));
                    break;
                case R.id.button7:
                    sum1 = sum1 + 2;
                    count1.setText(String.valueOf(sum1));
                    break;
                case R.id.button8:
                    sum1 = sum1 + 1;
                    count1.setText(String.valueOf(sum1));
                    break;
                case R.id.button10:
                    sum2 = sum2 + 3;
                    count2.setText(String.valueOf(sum2));
                    break;
                case R.id.button11:
                    sum2 = sum2 + 2;
                    count2.setText(String.valueOf(sum2));
                    break;
                case R.id.button12:
                    sum2 = sum2 + 1;
                    count2.setText(String.valueOf(sum2));
                    break;
                case R.id.button14:
                    sum1 = 0;
                    sum2 = 0;
                    count1.setText(String.valueOf(sum1));
                    count2.setText(String.valueOf(sum2));
                    break;

            }
        }
    };
}