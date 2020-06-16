package com.example.sharedprefsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedprefsdemo", Context.MODE_PRIVATE);

//        ArrayList<String> users = new ArrayList<>();
//        users.add("Keki");
//        users.add("Lolli");
//        users.add("Nancy");
//        users.add("Puski");
//
//        Log.i("Created Users:", users.toString());
//
//        try {
//            sharedPreferences.edit().putString("users",ObjectSerializer.serialize(users)).apply();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            ArrayList<String> newUsers = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("users",ObjectSerializer.serialize(new ArrayList<>())));

            Log.i("Fetched Users:",newUsers.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Username ",sharedPreferences.getString("userName",""));

    }
}