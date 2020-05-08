package com.spaceo.practicaltest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spaceo.practicaltest.Practical1.Practical1Activity
import com.spaceo.practicaltest.pratical3.Practical3Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListner()
    }


    private fun setListner() {
        btnPracticalFirst.setOnClickListener {
            startActivity(Intent(this, Practical1Activity::class.java))

        }
        btnPracticalSecond.setOnClickListener {
            startActivity(Intent(this, Practical3Activity::class.java))

        }
        btnPracticalThird.setOnClickListener {
            startActivity(Intent(this, Practical3Activity::class.java))
        }


    }
}