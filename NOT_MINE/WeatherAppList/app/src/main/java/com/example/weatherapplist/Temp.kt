package com.example.weatherapplist

import com.google.gson.annotations.SerializedName

data class Temp(
        @SerializedName("min")
        val min: Double,
        @SerializedName("max")
val max: Double
)