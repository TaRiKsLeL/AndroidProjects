package com.example.weatherapponeday

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var city: String = "Ivano-Frankivsk"
    var id: String ="aeca55359ace4e24dfe873cee9aa5c0c"
    var tempature: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherService()

        retry.setOnClickListener {
            weatherService()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putDouble("temperature",tempature)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tempature = savedInstanceState.getDouble("temperature")
        temp.text = String.format("%.2f",(tempature- 273.15))
    }

    @SuppressLint("CheckResult")
    fun weatherService() {
        //create api service
        val apiService = WeatherApiService.Factory.create().search(city, id)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ result ->
                    tempature = result!!.weatherTemp.temp
                    temp.text = String.format("%.2f",(tempature- 273.15))
                    retry.visibility = View.GONE
                }, { error ->
                    error.printStackTrace()
                    Toast.makeText(baseContext, "Ooops, error", Toast.LENGTH_LONG).show()
                    retry.visibility = View.VISIBLE
                })
    }

}