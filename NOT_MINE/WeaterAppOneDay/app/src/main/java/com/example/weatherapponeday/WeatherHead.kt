package com.example.weatherapponeday

import com.google.gson.annotations.SerializedName

data class WeatherHead(
        @SerializedName("main")
        val weatherTemp: Weather
)