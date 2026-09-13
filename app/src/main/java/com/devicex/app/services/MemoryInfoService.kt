package com.devicex.app.services

import android.app.ActivityManager
import android.content.Context

data class MemoryInfo(
    val totalBytes: Long,
    val availableBytes: Long,
    val usedBytes: Long
)

class MemoryInfoService(private val context: Context) {
    fun getMemoryInfo(): MemoryInfo {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memInfo)
        val total = memInfo.totalMem
        val available = memInfo.availMem
        val used = total - available
        return MemoryInfo(total, available, used)
    }
}
