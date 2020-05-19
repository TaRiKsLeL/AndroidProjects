package com.example.passingdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_h_r.*

class HRActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_r)
        val name = intent.getStringExtra("name")
        val familyName = intent.getStringExtra("familyName")
        nameHR.text = name
        familynameHR.text = familyName
    }
}
