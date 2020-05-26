package com.example.weatherapplist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class WeatherViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.weather_item, parent, false)) {
    private var date: TextView? = null
    private var temp: TextView? = null


    init {
        date = itemView.findViewById(R.id.date)
        temp = itemView.findViewById(R.id.temp)
    }

    fun bind(weather: WeatherList) {
        val dateW = Date( weather.dt * 1000)
        val sdf =  SimpleDateFormat("EEEE,MMMM d,yyyy", Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        date?.text = sdf.format(dateW)
        temp?.text = "${round(weather.temp.min - 273.15)} min    ${round(weather.temp.max - 273.15)} max"
    }

}