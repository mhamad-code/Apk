package com.devicex.app.services

import android.os.Build

object DeviceInfoService {
    fun getManufacturer(): String = Build.MANUFACTURER ?: "Not available"
    fun getModel(): String = Build.MODEL ?: "Not available"
    fun getAndroidVersion(): String = Build.VERSION.RELEASE ?: "Not available"
}
