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
fun AddReminderButton(navigateToDetails: () -> Unit) {
    FloatingActionButton(
        onClick = { navigateToDetails() },
        shape = RoundedCornerShape(50),
        containerColor = LocalColorScheme.current.backgroundColor,
        contentColor = LocalColorScheme.current.addIconColor,
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Reminder")
    }
}
