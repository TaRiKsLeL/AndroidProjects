package com.example.activitiesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Toast.makeText(this,intent.getStringExtra("friend")+"",Toast.LENGTH_SHORT).show();

    }

    public void goBack(View view){
        finish();
    }
}
