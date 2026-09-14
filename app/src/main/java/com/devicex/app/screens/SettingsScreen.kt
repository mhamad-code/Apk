package com.devicex.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devicex.app.R
import com.devicex.app.components.DeviceXCard
import com.devicex.app.components.DeviceXIconButton
import com.devicex.app.components.InfoRow
import com.devicex.app.ui.theme.AppTheme

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onAboutClick: () -> Unit,
    onContactClick: () -> Unit,
    onTermsClick: () -> Unit
) {
    val colors = AppTheme.colors
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isDarkTheme = androidx.compose.foundation.isSystemInDarkTheme()
    val versionName = remember(context.packageName) {
        try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
                ?: context.getString(R.string.not_available)
        } catch (e: Exception) {
            context.getString(R.string.not_available)
        }
    }
    val languageLabel = if (configuration.locales.get(0)?.language == "ar") {
        stringResource(R.string.settings_language_arabic)
    } else {
        stringResource(R.string.settings_language_english)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DeviceXIconButton(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                onClick = onBackClick
            )
            Text(
                text = stringResource(R.string.settings_title),
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 12.dp)
            )
        }

        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(
                    stringResource(R.string.settings_theme_current),
                    if (isDarkTheme) {
                        stringResource(R.string.settings_theme_dark)
                    } else {
                        stringResource(R.string.settings_theme_light)
                    }
                )
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(
                    stringResource(R.string.settings_language),
                    languageLabel
                )
            }

            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = onAboutClick
            ) {
                Text(
                    text = stringResource(R.string.settings_about),
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = onContactClick
            ) {
                Text(
                    text = stringResource(R.string.settings_contact),
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = onTermsClick
            ) {
                Text(
                    text = stringResource(R.string.settings_terms),
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(
                    stringResource(R.string.settings_version),
                    versionName
                )
            }
        }
    }
}
