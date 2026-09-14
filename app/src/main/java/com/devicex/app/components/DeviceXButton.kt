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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.devicex.app.ui.theme.AppTheme

@Composable
fun DeviceXButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    content: @Composable RowScope.() -> Unit
) {
    val colors = AppTheme.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.94f else 1f,
        animationSpec = if (isPressed && enabled) {
            tween(durationMillis = 90)
        } else {
            spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
        },
        label = "buttonScale"
    )
    val elevation by animateDpAsState(
        targetValue = if (isPressed && enabled) 2.dp else 8.dp,
        animationSpec = tween(durationMillis = 120),
        label = "buttonElevation"
    )
    val pressOverlayAlpha by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.18f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = "buttonPressOverlay"
    )

    Surface(
        modifier = modifier.scale(scale),
        shape = RoundedCornerShape(16.dp),
        color = colors.primary,
        shadowElevation = elevation
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colors.primary,
                            colors.cardGradientMid
                        )
                    )
                )
                .padding(contentPadding),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(colors.pressOverlay.copy(alpha = pressOverlayAlpha))
            )
        }
    }
}
