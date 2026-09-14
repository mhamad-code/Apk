package com.devicex.app.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.devicex.app.R
import com.devicex.app.components.DeviceXCard
import com.devicex.app.components.InfoRow
import com.devicex.app.ui.theme.AppTheme

@Composable
fun SettingsScreen(navController: NavHostController) {
    val colors = AppTheme.colors
    val context = LocalContext.current

    // نفس منطق قراءة الإصدار الموجود بالشاشة الترحيبية بالضبط، بدون أي رقم ثابت
    val versionName = remember {
        try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "?"
        } catch (e: Exception) {
            "?"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = colors.textPrimary
                )
            }
            Text(
                text = stringResource(R.string.settings_title),
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // بطاقة معلومات عامة: الثيم (تلقائي حسب النظام) + رقم الإصدار
            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.settings_theme_label), stringResource(R.string.settings_theme_value))
                InfoRow(stringResource(R.string.settings_language_label), stringResource(R.string.settings_language_value))
                InfoRow(stringResource(R.string.settings_version_label), versionName)
            }

            // روابط لصفحات لسا ما انبنت (About/Contact/Terms) — Toast مؤقت لحد ما نبنيها
            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, context.getString(R.string.settings_about), Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = stringResource(R.string.settings_about), color = colors.textPrimary, fontSize = 15.sp)
            }

            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, context.getString(R.string.settings_contact), Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = stringResource(R.string.settings_contact), color = colors.textPrimary, fontSize = 15.sp)
            }

            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, context.getString(R.string.settings_terms), Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = stringResource(R.string.settings_terms), color = colors.textPrimary, fontSize = 15.sp)
            }
        }
    }
}
