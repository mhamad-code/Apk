package com.devicex.app.screens

import android.content.Intent
import android.net.Uri
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
import com.devicex.app.ui.theme.AppTheme

private const val CONTACT_EMAIL = "mhamra148@gmail.com"
private const val CONTACT_GITHUB_URL = "https://github.com/mhamad-code/Apk"
private const val CONTACT_PHONE = "0951348968"

@Composable
fun ContactScreen(navController: NavHostController) {
    val colors = AppTheme.colors
    val context = LocalContext.current

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
                text = stringResource(R.string.settings_contact),
                color = colors.textPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // البريد: ACTION_SENDTO مع mailto: يفتح تطبيق البريد المثبت مباشرة على الإيميل الحقيقي
            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:$CONTACT_EMAIL")
                        }
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, context.getString(R.string.contact_no_app_found), Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = stringResource(R.string.contact_email_label), color = colors.textSecondary, fontSize = 13.sp)
                Text(
                    text = CONTACT_EMAIL,
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // GitHub: ACTION_VIEW يفتح المتصفح على رابط المستودع
            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(CONTACT_GITHUB_URL))
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, context.getString(R.string.contact_no_app_found), Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = stringResource(R.string.contact_github_label), color = colors.textSecondary, fontSize = 13.sp)
                Text(
                    text = CONTACT_GITHUB_URL,
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // الهاتف: ACTION_DIAL يفتح تطبيق الطلب مع تعبئة الرقم، بدون حاجة لصلاحية CALL_PHONE
            DeviceXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    try {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:$CONTACT_PHONE")
                        }
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, context.getString(R.string.contact_no_app_found), Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = stringResource(R.string.contact_phone_label), color = colors.textSecondary, fontSize = 13.sp)
                Text(
                    text = CONTACT_PHONE,
                    color = colors.textPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
