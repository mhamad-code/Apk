package com.devicex.app.services

import android.os.Environment
import android.os.StatFs

data class StorageInfo(
    val totalBytes: Long,
    val availableBytes: Long,
    val usedBytes: Long,
    val hasRemovableStorage: Boolean,
    val removableTotalBytes: Long?,
    val removableAvailableBytes: Long?
)

class StorageInfoService {
    fun getStorageInfo(): StorageInfo {
        val stat = StatFs(Environment.getDataDirectory().path)
        val total = stat.totalBytes
        val available = stat.availableBytes
        val used = total - available

        // Environment.isExternalStorageRemovable() يخبرنا إذا فيه بطاقة SD فعلية (مو تخزين داخلي مموّه كـ external)
        val isRemovable = try {
            Environment.isExternalStorageRemovable()
        } catch (e: Exception) {
            false
        }

        var removableTotal: Long? = null
        var removableAvailable: Long? = null

        if (isRemovable && Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                @Suppress("DEPRECATION")
                val extStat = StatFs(Environment.getExternalStorageDirectory().path)
                removableTotal = extStat.totalBytes
                removableAvailable = extStat.availableBytes
            } catch (e: Exception) {
                // تبقى null، بتعرض الواجهة "Not available"
            }
        }

        return StorageInfo(
            totalBytes = total,
            availableBytes = available,
            usedBytes = used,
            hasRemovableStorage = isRemovable,
            removableTotalBytes = removableTotal,
            removableAvailableBytes = removableAvailable
        )
    }
}
