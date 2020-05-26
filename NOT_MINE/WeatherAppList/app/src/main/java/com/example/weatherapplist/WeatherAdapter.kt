package com.example.weatherapplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class WeatherAdapter(private val list: List<WeatherList>)
    : RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return WeatherViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather: WeatherList = list[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int = list.size

}