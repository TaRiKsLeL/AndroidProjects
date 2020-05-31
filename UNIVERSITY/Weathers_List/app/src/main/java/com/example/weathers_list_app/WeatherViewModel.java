package com.example.weathers_list_app;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    WeatherRepository repository;
    MutableLiveData<List<Weather>> weathers;
    MutableLiveData<String> toastText;

    public WeatherViewModel(){
        repository = new WeatherRepository();
        weathers = repository.weathers;
        toastText = repository.toastText;
    }

    public void updateAllWeathersData(){repository.updateAllWeathersData();}
    public void addUpdateWeather(String city){
        repository.addUpdateWeather(city);
    }
    public void delete(Weather weather){repository.delete(weather);}
}
