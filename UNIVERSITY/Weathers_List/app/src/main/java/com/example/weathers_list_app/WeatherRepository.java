package com.example.weathers_list_app;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    public static String API_KEY = "aeca55359ace4e24dfe873cee9aa5c0c";
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    MutableLiveData<List<Weather>> weathers;
    MutableLiveData<String> toastText;

    List<Weather> newWeathersList;

    public WeatherRepository(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        weathers = new MutableLiveData<>();
        toastText = new MutableLiveData<>();
        newWeathersList = new ArrayList<>();
    }

    public void updateAllWeathersData(){
        for(Weather weather : newWeathersList){
            addUpdateWeather(weather.getName());
        }
    }

    public void addUpdateWeather(final String city){
        Call<WeatherPar> call = jsonPlaceHolderApi.getWeather(city,API_KEY);
        call.enqueue(new Callback<WeatherPar>() {
            @Override
            public void onResponse(Call<WeatherPar> call, Response<WeatherPar> response) {
                if(!response.isSuccessful()){
                    toastText.setValue("Не знайдено інформації");
                    return;
                }
                Weather newWeather = response.body().weatherTmp;
                newWeather.setName(city);
                newWeather.setTemp(newWeather.getTemp()- 273.15);
                if(!updatedWeather(newWeather)) {
                    newWeathersList.add(newWeather);
                    toastText.setValue("Добавлено нове місто");
                }
                weathers.setValue(newWeathersList);
            }

            @Override
            public void onFailure(Call<WeatherPar> call, Throwable t) {
                toastText.setValue(t.getMessage());
            }

        });
    }

    public boolean updatedWeather(Weather newWeather){
        for(Weather w : newWeathersList){
            if(w.getName().equals(newWeather.getName())){
                w.setTemp(newWeather.getTemp());
                return true;
            }
        }
        return false;
    }

    public void delete(Weather weather){
        newWeathersList.remove(weather);
        toastText.setValue("Місто видалено");
        weathers.setValue(newWeathersList);
    }

}
