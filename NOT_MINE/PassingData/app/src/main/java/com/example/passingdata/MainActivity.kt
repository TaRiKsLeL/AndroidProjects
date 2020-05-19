package com.example.passingdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/*https://developer.android.com/reference/android/widget/TextView.html#attr_android:inputType*/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, data?.getStringExtra("result"),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    fun ToHR(view: View) {
        val name = editName.text.toString()
        val familyName = editFamilyName.text.toString()
        val intent = Intent(this, HRActivity::class.java)
            .apply {
                putExtra("name", name)
                putExtra("familyName", familyName)
            }
        when(view.id) {
            R.id.buttonHR -> {
                val intent = Intent(this, HRActivity::class.java)
                    .apply {
                        putExtra("name", name)
                        putExtra("familyName", familyName)
                    }
                startActivity(intent)
            }
            R.id.buttonHRandBack -> {
                val intent = Intent(this, HRResultActivity::class.java)
                    .apply {
                        putExtra("name", name)
                        putExtra("familyName", familyName)
                    }
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    }


    companion object {
        private const val REQUEST_CODE = 0
    }
}
