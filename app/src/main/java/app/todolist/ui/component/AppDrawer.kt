package app.todolist.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.LibraryAddCheck
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import app.reminder.presentation.core.components.material.NavigationDrawerItem
import app.todolist.R
import app.todolist.ui.navigation.Tabs
import app.todolist.ui.theme.LocalColorScheme

@Composable
fun AppDrawer(
    currentRoute: String,
    modifier: Modifier = Modifier,
    navigateToReminder: () -> Unit,
    navigateToTrash: () -> Unit,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet(modifier, drawerContainerColor = LocalColorScheme.current.backgroundColor) {
        Text(
            "Personal Reminder",
            modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 15.dp),
            color = LocalColorScheme.current.foregroundColor,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        NavigationDrawerItem(
            modifier = Modifier.padding(
                start = NavigationDrawerItemDefaults.ItemPadding.calculateStartPadding(
                    LayoutDirection.Ltr
                ),
                top = NavigationDrawerItemDefaults.ItemPadding.calculateTopPadding(),
                end = NavigationDrawerItemDefaults.ItemPadding.calculateEndPadding(LayoutDirection.Rtl),
                bottom = NavigationDrawerItemDefaults.ItemPadding.calculateBottomPadding(),
            ),
            label = {
                Text(
                    stringResource(id = R.string.reminder_title),
                    fontWeight = if (currentRoute == Tabs.REMINDER_ROUTE) FontWeight.Bold else FontWeight.Normal
                )
            },
            icon = { Icon(Icons.Rounded.LibraryAddCheck, null) },
            selected = currentRoute == Tabs.REMINDER_ROUTE,
            onClick = { navigateToReminder(); closeDrawer() },
            shape = RoundedCornerShape(5),
            colors = NavigationDrawerItemDefaults.colors(
                unselectedTextColor = LocalColorScheme.current.foregroundColor,
                unselectedIconColor = LocalColorScheme.current.foregroundColor,
                selectedTextColor = LocalColorScheme.current.activeForegroundColor,
                selectedContainerColor = LocalColorScheme.current.activeBackgroundColor,
                selectedIconColor = LocalColorScheme.current.activeForegroundColor,
            )
        )
        NavigationDrawerItem(
            modifier = Modifier.padding(
                start = NavigationDrawerItemDefaults.ItemPadding.calculateStartPadding(
                    LayoutDirection.Ltr
                ),
                top = NavigationDrawerItemDefaults.ItemPadding.calculateTopPadding(),
                end = NavigationDrawerItemDefaults.ItemPadding.calculateEndPadding(LayoutDirection.Rtl),
                bottom = NavigationDrawerItemDefaults.ItemPadding.calculateBottomPadding(),
            ),
            label = {
                Text(
                    stringResource(id = R.string.trash_title),
                    fontWeight = if (currentRoute == Tabs.TRASH_ROUTE) FontWeight.Bold else FontWeight.Normal
                )
            },
            icon = { Icon(Icons.Rounded.Delete, null) },
            selected = currentRoute == Tabs.TRASH_ROUTE,
            onClick = { navigateToTrash(); closeDrawer() },
            shape = RoundedCornerShape(5),
            colors = NavigationDrawerItemDefaults.colors(
                unselectedTextColor = LocalColorScheme.current.foregroundColor,
                unselectedIconColor = LocalColorScheme.current.foregroundColor,
                selectedTextColor = LocalColorScheme.current.activeForegroundColor,
                selectedContainerColor = LocalColorScheme.current.activeBackgroundColor,
                selectedIconColor = LocalColorScheme.current.activeForegroundColor,
            )
        )
    }
}

@Composable
fun DottedHorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    thickness: Dp = 1.dp,
    dotRadius: Dp = 2.dp,
    spacing: Dp = 4.dp,
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(thickness)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val dotSpacing = spacing.toPx()
        val dotDiameter = dotRadius.toPx() * 2

        var currentX = 0f

        while (currentX < canvasWidth) {
            drawCircle(
                color = color,
                radius = dotRadius.toPx(),
                center = Offset(currentX + dotRadius.toPx(), canvasHeight / 2)
            )
            currentX += dotDiameter + dotSpacing
        }
    }
}

@Preview
@Composable
fun SampleScreen() {
    Column {
        Text("Text above the dotted divider")
        DottedHorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            dotRadius = 0.5.dp,
            spacing = 1.dp
        )
        Text("Text below the dotted divider")
    }
}
