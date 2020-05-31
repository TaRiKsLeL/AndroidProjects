package com.example.weather_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("weather")
    Call<WeatherPar> getWeather(@Query("q") String cityName, @Query("appid") String apeKey);
}
