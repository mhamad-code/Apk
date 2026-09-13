package com.devicex.app.services

import android.content.Context
import android.os.BatteryManager

class BatteryInfoService(private val context: Context) {
    fun getBatteryPercent(): Int {
        return try {
            val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } catch (e: Exception) {
            -1
        }
    }
}
