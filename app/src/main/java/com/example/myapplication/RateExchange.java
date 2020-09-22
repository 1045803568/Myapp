package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RateExchange extends AppCompatActivity {

    private TextView output;
    private EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_exchange);

        input = findViewById(R.id.editTextTextPersonName3);
        output = findViewById(R.id.textView10);
        Button b1 = findViewById(R.id.button9);
        Button b2 = findViewById(R.id.button13);
        Button b3 = findViewById(R.id.button15);

        b1.setOnClickListener(new RateExchange.BtnClickListener());
        b2.setOnClickListener(new RateExchange.BtnClickListener());
        b3.setOnClickListener(new RateExchange.BtnClickListener());

    }
    class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button9:
                    float f1 = Float.parseFloat(input.getText().toString());
                    output.setText(String.valueOf((f1*0.1474f)));
                    break;
                case R.id.button13:
                    float f2 = Float.parseFloat(input.getText().toString());
                    output.setText(String.valueOf((f2*0.1255f)));
                    break;
                case R.id.button15:
                    float f3 = Float.parseFloat(input.getText().toString());
                    output.setText(String.valueOf((f3*171.3606f)));
                    break;
            }
        }
    };
}