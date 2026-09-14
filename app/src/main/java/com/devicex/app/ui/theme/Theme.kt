package com.devicex.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// كل الألوان اللي يستخدمها التطبيق مجمّعة هنا، بنسختين: غامق وفاتح
data class AppColorScheme(
    val background: Color,
    val cardGradientTop: Color,
    val cardGradientBottom: Color,
    val primary: Color,
    val textPrimary: Color,
    val textSecondary: Color
)

private val DarkAppColors = AppColorScheme(
    background = Color(0xFF0A0C14),
    cardGradientTop = Color(0xFF1E2330),
    cardGradientBottom = Color(0xFF12151E),
    primary = Color(0xFFD4AF45),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFF8A94A8)
)

private val LightAppColors = AppColorScheme(
    background = Color(0xFFF2F3F7),
    cardGradientTop = Color(0xFFFFFFFF),
    cardGradientBottom = Color(0xFFE7E9F0),
    primary = Color(0xFFB8862E),
    textPrimary = Color(0xFF12151E),
    textSecondary = Color(0xFF5B6478)
)

// CompositionLocal: طريقة بـ Compose نوصل فيها للألوان من أي مكان بالتطبيق
// بدون ما نمررها يدويًا لكل Composable
private val LocalAppColors = staticCompositionLocalOf { DarkAppColors }

object AppTheme {
    val colors: AppColorScheme
        @Composable get() = LocalAppColors.current
}

@Composable
fun DeviceXTheme(content: @Composable () -> Unit) {
    // isSystemInDarkTheme() يقرأ إعداد النظام مباشرة، ويتحدث تلقائيًا لو المستخدم بدله
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
