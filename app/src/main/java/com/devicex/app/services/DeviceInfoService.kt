package com.devicex.app.services

import android.os.Build

object DeviceInfoService {
    fun getManufacturer(): String = Build.MANUFACTURER ?: "Not available"
    fun getModel(): String = Build.MODEL ?: "Not available"
    fun getBrand(): String = Build.BRAND ?: "Not available"
    fun getBoard(): String = Build.BOARD ?: "Not available"
    fun getHardware(): String = Build.HARDWARE ?: "Not available"
    fun getAndroidVersion(): String = Build.VERSION.RELEASE ?: "Not available"
    fun getSdkInt(): Int = Build.VERSION.SDK_INT
    fun getFingerprint(): String = Build.FINGERPRINT ?: "Not available"
}
