package com.example.weathers_list_app;

public class Weather {

    private double temp;

    private String name;

    public Weather(double temp) {
        this.temp = temp;
    }

    public Weather(String name, double temp) {
        this.name = name;
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                '}';
    }
}
