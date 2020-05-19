package com.example.passingdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_h_r.*
import kotlinx.android.synthetic.main.activity_h_r_result.*
import kotlinx.android.synthetic.main.activity_main.*

class HRResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_r_result)
        val name = intent.getStringExtra("name")
        val familyName = intent.getStringExtra("familyName")
        nameHRandBack.text = name
        familynameHRandBack.text = familyName
    }

    fun Back(view: View) {
        val result = editResult.text.toString()
        val intent = Intent()
        intent.putExtra("result", result)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
