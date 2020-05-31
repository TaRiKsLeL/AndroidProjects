package com.example.weathers_list_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("weather")
    Call<WeatherPar> getWeather(@Query("q") String cityName, @Query("appid") String apeKey);
}
