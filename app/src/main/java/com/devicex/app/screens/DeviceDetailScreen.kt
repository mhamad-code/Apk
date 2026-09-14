package com.devicex.app.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.devicex.app.R
import com.devicex.app.components.DeviceXCard
import com.devicex.app.components.InfoRow
import com.devicex.app.services.DeviceInfoService
import com.devicex.app.ui.theme.AppTheme

@Composable
fun DeviceDetailScreen(navController: NavHostController) {
    val colors = AppTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        // شريط علوي بسيط: زر رجوع + عنوان الشاشة
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
                text = stringResource(R.string.section_device),
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.device_field_manufacturer), DeviceInfoService.getManufacturer())
                InfoRow(stringResource(R.string.device_field_model), DeviceInfoService.getModel())
                InfoRow(stringResource(R.string.device_field_brand), DeviceInfoService.getBrand())
                InfoRow(stringResource(R.string.device_field_board), DeviceInfoService.getBoard())
                InfoRow(stringResource(R.string.device_field_hardware), DeviceInfoService.getHardware())
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.device_field_android_version), DeviceInfoService.getAndroidVersion())
                InfoRow(stringResource(R.string.device_field_sdk), DeviceInfoService.getSdkInt().toString())
                InfoRow(stringResource(R.string.device_field_fingerprint), DeviceInfoService.getFingerprint())
            }
        }
    }
}
