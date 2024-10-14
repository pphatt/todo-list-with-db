package app.todolist.presentation.screen.reminder.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import app.todolist.ui.navigation.Tabs
import app.todolist.ui.theme.LocalColorScheme

@Composable
fun AddReminderButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Tabs.DETAILS_ROUTE) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        },
        shape = RoundedCornerShape(50),
        containerColor = LocalColorScheme.current.backgroundColor,
        contentColor = LocalColorScheme.current.addIconColor,
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Reminder")
    }
}
