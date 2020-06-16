package com.example.weather_retrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;

public class WeatherViewModel extends ViewModel {
    WeatherRepository repository;
    MutableLiveData<Weather> weather;
    MutableLiveData<String> errorText;

    public WeatherViewModel(){
        repository = new WeatherRepository();
        weather = repository.weather;
        errorText = repository.errorText;
    }

    public void updateWeatherData(String city){
        repository.updateWeatherData(city);
    }
}
