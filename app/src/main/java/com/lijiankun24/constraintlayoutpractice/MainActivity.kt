package com.lijiankun24.constraintlayoutpractice

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Trace
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.beginSection("ConstraintLayout")
        }

        try {
            initView()
        } finally {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Trace.endSection()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_relative_positioning -> {
                startActivity(Intent(this, RelativePositionActivity::class.java))
            }
            R.id.tv_margins -> {
                startActivity(Intent(this, MarginsActivity::class.java))
            }
            R.id.tv_centering_positioning -> {
                startActivity(Intent(this, CenteringPositionActivity::class.java))
            }
            R.id.tv_circular_positioning -> {
                startActivity(Intent(this, CircularPositionActivity::class.java))
            }
            R.id.tv_visibility_behavior -> {
                startActivity(Intent(this, VisibilityBehaviorActivity::class.java))
            }
            R.id.tv_dimension_constraints -> {
                startActivity(Intent(this, DimensionConstraintsActivity::class.java))
            }
            R.id.tv_chains -> {
                startActivity(Intent(this, ChainsActivity::class.java))
            }
            R.id.tv_virtual_helpers_objects -> {
                startActivity(Intent(this, VirtualHelpersActivity::class.java))
            }
        }
    }

    private fun initView() {
        findViewById<View>(R.id.tv_relative_positioning).setOnClickListener(this)
        findViewById<View>(R.id.tv_margins).setOnClickListener(this)
        findViewById<View>(R.id.tv_centering_positioning).setOnClickListener(this)
        findViewById<View>(R.id.tv_circular_positioning).setOnClickListener(this)
        findViewById<View>(R.id.tv_visibility_behavior).setOnClickListener(this)
        findViewById<View>(R.id.tv_dimension_constraints).setOnClickListener(this)
        findViewById<View>(R.id.tv_chains).setOnClickListener(this)
        findViewById<View>(R.id.tv_virtual_helpers_objects).setOnClickListener(this)
    }
}
