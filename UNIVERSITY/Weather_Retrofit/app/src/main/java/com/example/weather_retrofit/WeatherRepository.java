package com.example.weather_retrofit;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    public static String API_KEY = "aeca55359ace4e24dfe873cee9aa5c0c";
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    MutableLiveData<Weather> weather;
    MutableLiveData<String> errorText;

    public WeatherRepository(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        weather = new MutableLiveData<>();
        errorText = new MutableLiveData<>();
    }

    public void updateWeatherData(String city){
        Call<WeatherPar> call = jsonPlaceHolderApi.getWeather(city,API_KEY);
        call.enqueue(new Callback<WeatherPar>() {
            @Override
            public void onResponse(Call<WeatherPar> call, Response<WeatherPar> response) {
                if(!response.isSuccessful()){
                    errorText.setValue("Не знайдено інформацію");
                    return;
                }
                Weather newWeather = response.body().weatherTmp;
                newWeather.setTemp(newWeather.getTemp()- 273.15);
                weather.setValue(newWeather);
            }

            @Override
            public void onFailure(Call<WeatherPar> call, Throwable t) {
                errorText.setValue(t.getMessage());
            }

        });
    }

}