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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devicex.app.R
import com.devicex.app.components.DeviceXCard
import com.devicex.app.components.DeviceXIconButton
import com.devicex.app.ui.theme.AppTheme

@Composable
fun TermsScreen(onBackClick: () -> Unit) {
    val colors = AppTheme.colors
    val termsSections = listOf(
        stringResource(R.string.terms_section_overview) to stringResource(R.string.terms_overview),
        stringResource(R.string.terms_section_device_information) to stringResource(R.string.terms_device_information),
        stringResource(R.string.terms_section_privacy) to stringResource(R.string.terms_privacy),
        stringResource(R.string.terms_section_permissions) to stringResource(R.string.terms_permissions),
        stringResource(R.string.terms_section_accuracy) to stringResource(R.string.terms_accuracy),
        stringResource(R.string.terms_section_user_responsibility) to stringResource(R.string.terms_user_responsibility),
        stringResource(R.string.terms_section_changes) to stringResource(R.string.terms_changes)
    )

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
                text = stringResource(R.string.terms_title),
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
            termsSections.forEach { (title, body) ->
                DeviceXCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        color = colors.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = body,
                        color = colors.textSecondary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
