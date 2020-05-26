package com.example.weatherapplist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callApi()

        retry.setOnClickListener {
            callApi()
        }
    }

    @SuppressLint("CheckResult")
    fun callApi() {
        //create api service
        val apiService = WeatherApiService.Factory.create()
        //call service with weather
        apiService.search("48.921341", "24.710229", "05b48cdbb814c0cd2879a4ed4d1f882e", "hourly,minutely")
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ result ->
                    //get response for temp and view on screen
                    temp.append("${round(result!!.weatherList[0].temp.min - 273.15)} min      ${round(result!!.weatherList[0].temp.max - 273.15)} max")
                    retry.visibility = View.GONE
                    var list = result.weatherList.toMutableList()
                    //delete today weather
                    list.removeAt(0)
                    //apply rv setup adapter
                    rv.apply {
                        // set a LinearLayoutManager to handle Android
                        // RecyclerView behavior
                        layoutManager = LinearLayoutManager(baseContext)
                        // set the custom adapter to the RecyclerView
                        adapter = WeatherAdapter(list)
                    }
                }, { error ->
                    //error handling
                    error.printStackTrace()
                    Toast.makeText(baseContext, "Some error", Toast.LENGTH_LONG).show()
                    retry.visibility = View.VISIBLE
                })
    }

}