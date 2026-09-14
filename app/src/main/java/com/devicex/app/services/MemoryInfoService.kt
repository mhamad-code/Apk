package com.devicex.app.services

import android.app.ActivityManager
import android.content.Context

data class MemoryInfo(
    val totalBytes: Long,
    val availableBytes: Long,
    val usedBytes: Long,
    val lowMemoryThresholdBytes: Long,
    val isLowMemory: Boolean
)

class MemoryInfoService(private val context: Context) {
    fun getMemoryInfo(): MemoryInfo {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memInfo)
        val total = memInfo.totalMem
        val available = memInfo.availMem
        val used = total - available
        return MemoryInfo(
            totalBytes = total,
            availableBytes = available,
            usedBytes = used,
            lowMemoryThresholdBytes = memInfo.threshold,
            isLowMemory = memInfo.lowMemory
        )
    }
}
