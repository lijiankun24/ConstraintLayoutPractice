package com.lijiankun24.constraintlayoutpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class VisibilityBehaviorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visibility_behavior)
        var webview = findViewById<WebView>(R.id.webview)
        webview.settings.javaScriptEnabled = true
    }
}
