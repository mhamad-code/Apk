package com.devicex.app.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
    // onClick اختياري: البطاقات اللي بدها تتفاعل (زي أقسام الشاشة الرئيسية) تمرره،
    // والبطاقات اللي بس تعرض بيانات (زي بطاقة الملخص) تتركه فاضي فما يصير فيها تأثير ضغط
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val colors = AppTheme.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // لما تنضغط البطاقة: تصغر شوي وينخفض الظل، إحساس إنها انضغطت للداخل فعليًا (تأثير 3D)
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        label = "cardScale"
    )
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 4.dp else 10.dp,
        label = "cardElevation"
    )

    var cardModifier = modifier.scale(scale)
    if (onClick != null) {
        cardModifier = cardModifier.clickable(
            interactionSource = interactionSource,
            indication = null, // ما بنستخدم ripple الافتراضي، تأثير الـ scale/elevation بديل عنه
            onClick = onClick
        )
    }

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colors.cardGradientTop),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(colors.cardGradientTop, colors.cardGradientBottom)
                    )
                )
                .padding(16.dp),
            content = content
        )
    }
}
