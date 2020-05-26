package com.example.weatherapplist

import com.google.gson.annotations.SerializedName

data class WeatherHead(
        @SerializedName("daily")
        val weatherList: List<WeatherList>
)