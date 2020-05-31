package com.example.weather_retrofit;

import com.google.gson.annotations.SerializedName;

public class WeatherPar {
    @SerializedName("main")
    public Weather weatherTmp;

    @Override
    public String toString() {
        return "WeatherPar{" +
                "weatherTmp=" + weatherTmp +
                '}';
    }
}
