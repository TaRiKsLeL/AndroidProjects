package com.example.weatherapplist

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    //request for weather
    @GET("onecall")
    fun search(@Query("lat") lat: String?,
               @Query("lon") lon: String?,
               @Query("appid") appid: String,
               @Query("exclude") exclude: String)
            : Observable<WeatherHead?>?

    /**
     * Factory class for convenient creation of the Api Service interface
     */
    object Factory {
        fun create(): WeatherApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.openweathermap.org/data/2.5/")
                    .build()
            return retrofit.create(WeatherApiService::class.java)
        }
    }
}