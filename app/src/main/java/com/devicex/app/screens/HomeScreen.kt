package com.devicex.app.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import com.devicex.app.navigation.Routes
import com.devicex.app.services.BatteryInfoService
import com.devicex.app.services.DeviceInfoService
import com.devicex.app.services.MemoryInfoService
import com.devicex.app.services.StorageInfoService
import com.devicex.app.ui.theme.AppTheme
import com.devicex.app.utils.formatBytes

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val colors = AppTheme.colors

    val memoryService = remember { MemoryInfoService(context) }
    val storageService = remember { StorageInfoService() }
    val batteryService = remember { BatteryInfoService(context) }

    val memoryInfo = remember { memoryService.getMemoryInfo() }
    val storageInfo = remember { storageService.getStorageInfo() }
    val batteryPercent = remember { batteryService.getBatteryPercent() }

    val sections = listOf(
        R.string.section_device,
        R.string.section_cpu,
        R.string.section_ram,
        R.string.section_storage,
        R.string.section_battery,
        R.string.section_display,
        R.string.section_gpu,
        R.string.section_sensors,
        R.string.section_camera,
        R.string.section_network,
        R.string.section_audio,
        R.string.section_hardware,
        R.string.section_tests
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DeviceX",
                    color = colors.textPrimary,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                IconButton(onClick = { navController.navigate(Routes.SETTINGS) }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(R.string.settings_title),
                        tint = colors.textPrimary
                    )
                }
            }
        }

        item(span = { GridItemSpan(2) }) {
            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.home_device_model), DeviceInfoService.getModel())
                InfoRow(stringResource(R.string.home_android_version), DeviceInfoService.getAndroidVersion())
                InfoRow(stringResource(R.string.home_ram), formatBytes(memoryInfo.totalBytes))
                InfoRow(stringResource(R.string.home_storage), formatBytes(storageInfo.totalBytes))
                InfoRow(
                    stringResource(R.string.home_battery),
                    if (batteryPercent >= 0) "$batteryPercent%" else "Not available"
                )
            }
        }

        item(span = { GridItemSpan(2) }) {
            Text(
                text = stringResource(R.string.home_sections_title),
                color = colors.textSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
        }

        items(sections) { sectionRes ->
            val sectionName = stringResource(sectionRes)
            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    when (sectionRes) {
                        R.string.section_device -> navController.navigate(Routes.DEVICE_DETAIL)
                        R.string.section_cpu -> navController.navigate(Routes.CPU_DETAIL)
                        R.string.section_ram -> navController.navigate(Routes.RAM_DETAIL)
                        else -> Toast.makeText(context, sectionName, Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(
                    text = sectionName,
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
