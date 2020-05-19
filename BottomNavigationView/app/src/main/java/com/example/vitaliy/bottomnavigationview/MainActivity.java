package com.example.vitaliy.bottomnavigationview;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textSOS;
    private TextView textList;
    private TextView textMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSOS = findViewById(R.id.text_sos);
        textList = findViewById(R.id.text_list);
        textMap = findViewById(R.id.text_map);

        BottomNavigationView bottomNavigationView =
                findViewById(R.id.main_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
                    case R.id.action_sos:
                        textSOS.setVisibility(View.VISIBLE);
                        textList.setVisibility(View.GONE);
                        textMap.setVisibility(View.GONE);
                        break;
                    case R.id.action_list:
                        textSOS.setVisibility(View.GONE);
                        textList.setVisibility(View.VISIBLE);
                        textMap.setVisibility(View.GONE);
                        break;
                    case R.id.action_map:
                        textSOS.setVisibility(View.GONE);
                        textList.setVisibility(View.GONE);
                        textMap.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });



    }
}