package com.devicex.app.services

import android.os.Environment
import android.os.StatFs

data class StorageInfo(
    val totalBytes: Long,
    val availableBytes: Long,
    val usedBytes: Long
)

class StorageInfoService {
    fun getStorageInfo(): StorageInfo {
        val stat = StatFs(Environment.getDataDirectory().path)
        val total = stat.totalBytes
        val available = stat.availableBytes
        val used = total - available
        return StorageInfo(total, available, used)
    }
}
