package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView myListView = findViewById(R.id.myListView);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            ArrayAdapter arrayAdapter;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                arrayAdapter = new ArrayAdapter
                        (getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        createTimesTableByNum(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myListView.setAdapter(arrayAdapter);
            }
        });
    }

    ArrayList<Integer> createTimesTableByNum(int num){
        ArrayList<Integer> timesArray=new ArrayList<>();

        for(int i=1;i<=10;i++){
            timesArray.add(num*i);
        }
        return timesArray;
    }
}
