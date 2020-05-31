package com.example.weathers_list_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //=======================
    WeatherViewModel weatherViewModel;

    //==========UI===========
    EditText cityNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityNameEditText = findViewById(R.id.cityNameEditText);
        RecyclerView recyclerView = findViewById(R.id.weathersRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final WeatherAdapter adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.weathers.observe(this, new Observer<List<Weather>>() {
            @Override
            public void onChanged(List<Weather> weathers) {
                adapter.setWeathers(weathers);
            }
        });

        weatherViewModel.toastText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                weatherViewModel.delete(adapter.getWeatherAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void addSearchTemp(View view) {
        weatherViewModel.addUpdateWeather(cityNameEditText.getText().toString());
    }

    public void updateData(View view) {
        weatherViewModel.updateAllWeathersData();
    }
}