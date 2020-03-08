package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convertClick(View view){

        EditText amountEditText = findViewById(R.id.amountEditText);

        float dollars = Float.parseFloat(amountEditText.getText().toString());
        //float grivnaPerDollar =  String.valueOf(getResources().getInteger(R.integer.grivna));
        float result = dollars*grivnaPerDollar;

        Toast.makeText(this,dollars+ " dollars into grivnas: "+result,Toast.LENGTH_SHORT).show();
    }
}
