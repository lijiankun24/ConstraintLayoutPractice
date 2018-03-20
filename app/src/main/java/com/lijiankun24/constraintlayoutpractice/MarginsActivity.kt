package com.lijiankun24.constraintlayoutpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MarginsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_margins)
        findViewById<TextView>(R.id.tv_b).setOnClickListener{
            findViewById<TextView>(R.id.tv_a).visibility = View.GONE
        }
    }
}
