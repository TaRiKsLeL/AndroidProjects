package com.example.weatherapplist

import com.google.gson.annotations.SerializedName

data class WeatherList(
        @SerializedName("temp")
        val temp: Temp,
        @SerializedName("dt")
        val dt: Long
)