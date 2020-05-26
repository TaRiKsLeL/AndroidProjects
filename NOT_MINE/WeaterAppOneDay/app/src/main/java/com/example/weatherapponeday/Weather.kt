package com.example.weatherapponeday

import com.google.gson.annotations.SerializedName

data class Weather(
        @SerializedName("temp")
        val temp: Double
)