package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class Rate_config extends AppCompatActivity {

    private static final String TAG = "Rate_config";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_config);

        EditText d1 = findViewById(R.id.editTextTextPersonName4);
        EditText e1 = findViewById(R.id.editTextTextPersonName5);
        EditText w1 = findViewById(R.id.editTextTextPersonName6);

        Intent itent = getIntent();
        float dallar = itent.getFloatExtra("dollar_rate",0.0000f);
        float euro = itent.getFloatExtra("euro_rate",0.0000f);
        float won = itent.getFloatExtra("won_rate",0.0000f);

        d1.setText(String.valueOf(dallar));
        e1.setText(String.valueOf(euro));
        w1.setText(String.valueOf(won));

        Log.i(TAG,"openOne:dallarRate"+dallar);
        Log.i(TAG,"openOne:euroRate"+euro);
        Log.i(TAG,"openOne:wonRate"+won);

    }
}