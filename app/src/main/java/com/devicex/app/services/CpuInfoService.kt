package com.devicex.app.services

import android.os.Build
import java.io.File
import java.io.RandomAccessFile

data class CpuCoreInfo(
    val coreIndex: Int,
    val currentFreqMHz: Int?,
    val maxFreqMHz: Int?
)

object CpuInfoService {

    fun getCoreCount(): Int = Runtime.getRuntime().availableProcessors()

    fun getSupportedAbis(): List<String> = Build.SUPPORTED_ABIS?.toList() ?: emptyList()

    // /proc/cpuinfo متاح للقراءة على كل الأجهزة، لكن اسم السطر يختلف حسب المعالج
    fun getCpuHardwareName(): String {
        return try {
            File("/proc/cpuinfo").readLines()
                .firstOrNull { it.startsWith("Hardware") || it.startsWith("model name") }
                ?.substringAfter(":")
                ?.trim()
                ?.takeIf { it.isNotEmpty() }
                ?: "Not available"
        } catch (e: Exception) {
            "Not available"
        }
    }

    // ملفات cpufreq تحت /sys قد تكون محجوبة على بعض الأجهزة حسب الشركة المصنّعة
    fun getCoreInfos(): List<CpuCoreInfo> {
        val count = getCoreCount()
        return (0 until count).map { index ->
            CpuCoreInfo(
                coreIndex = index,
                currentFreqMHz = readFreqKhzAsMhz("/sys/devices/system/cpu/cpu$index/cpufreq/scaling_cur_freq"),
                maxFreqMHz = readFreqKhzAsMhz("/sys/devices/system/cpu/cpu$index/cpufreq/cpuinfo_max_freq")
            )
        }
    }

    private fun readFreqKhzAsMhz(path: String): Int? {
        return try {
            val raf = RandomAccessFile(path, "r")
            val value = raf.readLine()?.trim()?.toLongOrNull()
            raf.close()
            value?.let { (it / 1000).toInt() }
        } catch (e: Exception) {
            null
        }
    }
}
