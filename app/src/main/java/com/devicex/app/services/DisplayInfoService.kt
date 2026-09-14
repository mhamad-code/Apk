package com.devicex.app.services

import android.content.Context
import android.view.WindowManager
import kotlin.math.sqrt

data class DisplayInfo(
    val widthPx: Int,
    val heightPx: Int,
    val densityDpi: Int,
    val densityScale: Float,
    val refreshRateHz: Float,
    val diagonalInches: Float
)

class DisplayInfoService(private val context: Context) {
    fun getDisplayInfo(): DisplayInfo {
        val metrics = context.resources.displayMetrics

        // defaultDisplay قديمة (deprecated) بس تشتغل من API 24 لحد أحدث إصدار، وما فيه بديل بسيط يغطي minSdk 24
        val refreshRate = try {
            @Suppress("DEPRECATION")
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.refreshRate
        } catch (e: Exception) {
            0f
        }

        // حساب قطر الشاشة بالإنش من دقة البكسل وكثافة النقاط الفعلية (xdpi/ydpi)
        val diagonal = try {
            val widthInches = metrics.widthPixels / metrics.xdpi
            val heightInches = metrics.heightPixels / metrics.ydpi
            sqrt((widthInches * widthInches + heightInches * heightInches).toDouble()).toFloat()
        } catch (e: Exception) {
            0f
        }

        return DisplayInfo(
            widthPx = metrics.widthPixels,
            heightPx = metrics.heightPixels,
            densityDpi = metrics.densityDpi,
            densityScale = metrics.density,
            refreshRateHz = refreshRate,
            diagonalInches = diagonal
        )
    }
}
