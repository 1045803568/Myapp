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

        b1.setOnClickListener(button_listener);
    }

    Button.OnClickListener button_listener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            double input1 = Double.parseDouble(input.getText().toString());
            output.setText((int) (input1-32))/18);
        }
    };
}