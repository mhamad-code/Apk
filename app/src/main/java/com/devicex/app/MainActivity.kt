package com.devicex.app

import android.app.Activity
import android.os.Bundle
import android.os.Build
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(48, 48, 48, 48)
        }

        val title = TextView(this).apply {
            text = "DeviceX"
            textSize = 32f
            gravity = Gravity.CENTER
        }

        val model = TextView(this).apply {
            text = "${Build.MANUFACTURER} ${Build.MODEL}"
            textSize = 20f
            gravity = Gravity.CENTER
        }

        val androidVersion = TextView(this).apply {
            text = "Android ${Build.VERSION.RELEASE}"
            textSize = 18f
            gravity = Gravity.CENTER
        }

        root.addView(title)
        root.addView(model)
        root.addView(androidVersion)

        setContentView(root)
    }
}
