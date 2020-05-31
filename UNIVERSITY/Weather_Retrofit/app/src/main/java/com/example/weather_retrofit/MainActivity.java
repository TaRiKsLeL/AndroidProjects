package com.example.weather_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //=======================
    WeatherViewModel weatherViewModel;

    //==========UI===========
    EditText cityNameEditText;
    TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameEditText =  findViewById(R.id.cityNameEditText);
        temperatureTextView = findViewById(R.id.weatherTemperatureTV);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.weather.observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                temperatureTextView.setText(String.format("%.2f",(weather.getTemp())));
            }
        });

        weatherViewModel.errorText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
            }
        });

    }

    public void searchTemp(View view){
        weatherViewModel.updateWeatherData(cityNameEditText.getText().toString());

    }

}