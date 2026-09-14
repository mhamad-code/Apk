package com.devicex.app.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.devicex.app.R
import com.devicex.app.components.DeviceXCard
import com.devicex.app.components.InfoRow
import com.devicex.app.services.MemoryInfoService
import com.devicex.app.ui.theme.AppTheme
import com.devicex.app.utils.formatBytes

@Composable
fun RamDetailScreen(navController: NavHostController) {
    val colors = AppTheme.colors
    val context = LocalContext.current

    val memoryService = remember { MemoryInfoService(context) }
    val memoryInfo = remember { memoryService.getMemoryInfo() }

    val usagePercent = if (memoryInfo.totalBytes > 0) {
        (memoryInfo.usedBytes.toFloat() / memoryInfo.totalBytes.toFloat()) * 100f
    } else 0f

    val animatedProgress by animateFloatAsState(
        targetValue = usagePercent / 100f,
        animationSpec = tween(durationMillis = 700),
        label = "ramUsageProgress"
    )

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
                text = stringResource(R.string.section_ram),
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
                Text(
                    text = stringResource(R.string.ram_field_usage_percent, usagePercent),
                    color = colors.textPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(colors.cardGradientBottom)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedProgress.coerceIn(0f, 1f))
                            .height(14.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(colors.primary, colors.textPrimary.copy(alpha = 0.6f))
                                )
                            )
                    )
                }
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.ram_field_total), formatBytes(memoryInfo.totalBytes))
                InfoRow(stringResource(R.string.ram_field_used), formatBytes(memoryInfo.usedBytes))
                InfoRow(stringResource(R.string.ram_field_available), formatBytes(memoryInfo.availableBytes))
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.ram_field_threshold), formatBytes(memoryInfo.lowMemoryThresholdBytes))
                InfoRow(
                    stringResource(R.string.ram_field_low_memory),
                    if (memoryInfo.isLowMemory) stringResource(R.string.yes) else stringResource(R.string.no)
                )
            }
        }
    }
}
