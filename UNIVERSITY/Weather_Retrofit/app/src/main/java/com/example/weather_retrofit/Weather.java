package com.example.weather_retrofit;

import androidx.lifecycle.LiveData;

import com.google.gson.annotations.SerializedName;

public class Weather {

   // @SerializedName("main")
    private double temp;


    public Weather(double temp) {
        this.temp = temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                '}';
    }
}
