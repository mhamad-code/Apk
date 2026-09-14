package com.devicex.app.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.devicex.app.ui.theme.AppTheme

@Composable
fun DeviceXCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = AppTheme.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // نزول محسوس (7%) بحركة سريعة عند الضغط، وارتداد "نطّة" خفيفة عند الرفع
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.93f else 1f,
        animationSpec = if (isPressed) {
            tween(durationMillis = 90)
        } else {
            spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
        },
        label = "cardScale"
    )

    // قفزة ظل كبيرة: من 14dp (بطاقة "طايرة") لـ 2dp (بطاقة "منضغطة بالسطح")
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 14.dp,
        animationSpec = tween(durationMillis = 120),
        label = "cardElevation"
    )

    // طبقة تعتيم خفيفة فوق البطاقة لحظة الضغط، تعطي إحساس لمسي إضافي
    val pressOverlayAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.20f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = "cardPressOverlay"
    )

    var cardModifier = modifier.scale(scale)
    if (onClick != null) {
        cardModifier = cardModifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    }

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colors.cardGradientTop),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                colors.cardGradientTop,
                                colors.cardGradientMid,
                                colors.cardGradientBottom
                            )
                        )
                    )
                    .padding(16.dp),
                content = content
            )

            if (onClick != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.pressOverlay.copy(alpha = pressOverlayAlpha))
                )
            }
        }
    }
}
