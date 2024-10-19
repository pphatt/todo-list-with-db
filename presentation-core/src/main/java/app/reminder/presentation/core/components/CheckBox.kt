package app.reminder.presentation.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val animatedColor by animateColorAsState(
        if (checked) Color.Black else Color.Transparent,
        label = "color"
    )

    Box(
        modifier = modifier
        .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
        .height(20.dp)
        .width(20.dp)
        .drawBehind {
            val cornerRadius =
                5.dp.toPx() // must match the RoundedCornerShape(5.dp)
            drawRoundRect(
                color = animatedColor,
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )
        }
        .clip(
            RoundedCornerShape(5.dp)
        )
        .clickable {
            onCheckedChange(!checked)
        }
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            AnimatedVisibility(
                checked,
                enter = scaleIn(initialScale = 0.5f),
                exit = shrinkOut(shrinkTowards = Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "checked",
                    tint = Color.White
                )
            }
        }

    }
}
