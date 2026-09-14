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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.devicex.app.services.CpuCoreInfo
import com.devicex.app.services.CpuInfoService
import com.devicex.app.ui.theme.AppTheme

@Composable
fun CpuDetailScreen(navController: NavHostController) {
    val colors = AppTheme.colors

    val coreCount = remember { CpuInfoService.getCoreCount() }
    val abis = remember { CpuInfoService.getSupportedAbis() }
    val hardwareName = remember { CpuInfoService.getCpuHardwareName() }
    val coreInfos = remember { CpuInfoService.getCoreInfos() }

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
                text = stringResource(R.string.section_cpu),
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.cpu_field_cores), coreCount.toString())
                InfoRow(
                    stringResource(R.string.cpu_field_architecture),
                    if (abis.isNotEmpty()) abis.joinToString(", ") else "Not available"
                )
                InfoRow(stringResource(R.string.cpu_field_hardware), hardwareName)
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.cpu_per_core_title),
                    color = colors.textSecondary,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                coreInfos.forEach { core ->
                    InfoRow(
                        stringResource(R.string.cpu_core_label, core.coreIndex),
                        formatCoreFreq(core)
                    )
                }
            }
        }
    }
}

private fun formatCoreFreq(core: CpuCoreInfo): String {
    val cur = core.currentFreqMHz
    val max = core.maxFreqMHz
    return when {
        cur != null && max != null -> "$cur / $max MHz"
        cur != null -> "$cur MHz"
        max != null -> "≤ $max MHz"
        else -> "Not available"
    }
}
