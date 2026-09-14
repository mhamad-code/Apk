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
import com.devicex.app.services.DisplayInfoService
import com.devicex.app.ui.theme.AppTheme

@Composable
fun DisplayDetailScreen(navController: NavHostController) {
    val colors = AppTheme.colors
    val context = LocalContext.current

    val displayService = remember { DisplayInfoService(context) }
    val displayInfo = remember { displayService.getDisplayInfo() }

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
                text = stringResource(R.string.section_display),
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
                InfoRow(
                    stringResource(R.string.display_field_resolution),
                    "${displayInfo.widthPx} x ${displayInfo.heightPx}"
                )
                InfoRow(
                    stringResource(R.string.display_field_diagonal),
                    if (displayInfo.diagonalInches > 0f) {
                        "%.1f\"".format(displayInfo.diagonalInches)
                    } else {
                        "Not available"
                    }
                )
            }

            DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                InfoRow(stringResource(R.string.display_field_density), "${displayInfo.densityDpi} dpi")
                InfoRow(stringResource(R.string.display_field_density_scale), "${displayInfo.densityScale}x")
                InfoRow(
                    stringResource(R.string.display_field_refresh_rate),
                    if (displayInfo.refreshRateHz > 0f) {
                        "%.0f Hz".format(displayInfo.refreshRateHz)
                    } else {
                        "Not available"
                    }
                )
            }
        }
    }
}
