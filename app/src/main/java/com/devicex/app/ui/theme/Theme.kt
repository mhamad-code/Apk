package com.devicex.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorScheme(
    val background: Color,
    val cardGradientTop: Color,
    val cardGradientMid: Color,
    val cardGradientBottom: Color,
    val primary: Color,
    val textPrimary: Color,
    val textSecondary: Color
)

// 3 درجات بنفس عائلة الأزرق الغامق، بس متباعدة كفاية إنها تعطي إحساس عمق حقيقي
private val DarkAppColors = AppColorScheme(
    background = Color(0xFF0A0C14),
    cardGradientTop = Color(0xFF2A3150),
    cardGradientMid = Color(0xFF181D2E),
    cardGradientBottom = Color(0xFF0C0E16),
    primary = Color(0xFFD4AF45),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFF8A94A8)
)

// نفس الفكرة بالفاتح: أبيض -> رمادي فاتح مائل للبنفسجي -> رمادي أزرق فاتح
private val LightAppColors = AppColorScheme(
    background = Color(0xFFF2F3F7),
    cardGradientTop = Color(0xFFFFFFFF),
    cardGradientMid = Color(0xFFEDEFF7),
    cardGradientBottom = Color(0xFFDBDEEA),
    primary = Color(0xFFB8862E),
    textPrimary = Color(0xFF12151E),
    textSecondary = Color(0xFF5B6478)
)

private val LocalAppColors = staticCompositionLocalOf { DarkAppColors }

object AppTheme {
    val colors: AppColorScheme
        @Composable get() = LocalAppColors.current
}

@Composable
fun DeviceXTheme(content: @Composable () -> Unit) {
    val isDark = isSystemInDarkTheme()
    val appColors = if (isDark) DarkAppColors else LightAppColors

    val materialScheme = if (isDark) {
        darkColorScheme(
            background = appColors.background,
            surface = appColors.background,
            primary = appColors.primary
        )
    } else {
        lightColorScheme(
            background = appColors.background,
            surface = appColors.background,
            primary = appColors.primary
        )
    }

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(colorScheme = materialScheme) {
            content()
        }
    }
}
