package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Temp extends AppCompatActivity {

    private TextView output;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        input = findViewById(R.id.editTextTextPersonName2);
        output = findViewById(R.id.textView3);
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button1);

        b1.setOnClickListener(button_listener);
        b2.setOnClickListener(button_listener1);
    }

    Button.OnClickListener button_listener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            float f = Float.parseFloat(input.getText().toString());
            output.setText(getResources().getString(R.string.Centigradedegree)+String.valueOf(((f-32.0f)/1.8f)));
        }
    };
    Button.OnClickListener button_listener1 = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            float f = Float.parseFloat(input.getText().toString());
            output.setText(getResources().getString(R.string.Fahrenheitdegree)+String.valueOf((f*1.8f)+32f));
        }
    };
}