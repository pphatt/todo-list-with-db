package app.todolist.presentation.details.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.todolist.ui.navigation.NavigationActions
import app.todolist.ui.theme.LocalColorScheme

@Composable
fun KeyboardAware(
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.imePadding()) {
        content()
    }
}

@Composable
fun BottomAppBarDefaults(
    modifier: Modifier = Modifier, navigationActions: NavigationActions
) {
    KeyboardAware {
        Surface(
            color = LocalColorScheme.current.primaryBackgroundColor
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 5.dp, end = 20.dp)
                    .navigationBarsPadding()
                    ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    title = "Exit",
                    onClick = { navigationActions.navigateToReminder() },
                )

                Button(
                    title = "Save",
                    enabled = false,
                    onClick = { navigationActions.navigateToReminder() },
                )
            }
        }
    }
}

@Composable
private fun RowScope.Button(
    title: String,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(50))
            .combinedClickable(
                onClick = onClick,
            )
            .height(40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                )
            }

            Text(
                text = title,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                color = if (enabled) Color(0xFF272828) else Color(0xFFadb1b4),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            content?.invoke()
        }
    }
}
