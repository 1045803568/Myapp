package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Counter extends AppCompatActivity {
    private TextView count;
    int sum = 0;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        count = findViewById(R.id.textView4);
        bt1 = findViewById(R.id.button2);
        bt2 = findViewById(R.id.button3);
        bt3 = findViewById(R.id.button4);
        bt4 = findViewById(R.id.button5);

        bt1.setOnClickListener(new BtnClickListener());
        bt2.setOnClickListener(new BtnClickListener());
        bt3.setOnClickListener(new BtnClickListener());
        bt4.setOnClickListener(new BtnClickListener());
    }
    static class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button2:
                    sum = sum + 1;
                    count.setText(String.valueOf(sum));
                    break;
                case R.id.button3:
                    sum = sum + 2;
                    count.setText(String.valueOf(sum));
                    break;
                case R.id.button4:
                    sum = sum + 3;
                    count.setText(String.valueOf(sum));
                    break;
                case R.id.button5:
                    sum = 0;
                    count.setText(String.valueOf(sum));
                    break;

            }
        }
    };
}
