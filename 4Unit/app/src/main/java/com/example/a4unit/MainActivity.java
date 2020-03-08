package com.example.a4unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView homerImageView;
    boolean homerFaded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homerImageView = findViewById(R.id.homerImageView);
        homerImageView.animate().translationXBy(-1500).scaleX(0.5f).scaleY(0.5f).alpha(0);
    }

    public void fade(View view){

        homerImageView.animate().translationXBy(1500).rotation(1800).scaleY(1).scaleX(1).alpha(1).setDuration(2000);

        //view.animate().translationXBy(-100).setDuration(2000);

//        if(homerFaded) {
//            view.animate().alpha(1).setDuration(2000);
//            findViewById(R.id.bartImageView).animate().alpha(0).setDuration(2000);
//            homerFaded=false;
//        }
//        else{
//            view.animate().alpha(0).setDuration(2000);
//            findViewById(R.id.bartImageView).animate().alpha(1).setDuration(2000);
//            homerFaded=true;
//        }

    }
}
