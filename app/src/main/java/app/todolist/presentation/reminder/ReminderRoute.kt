package app.todolist.presentation.reminder

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import app.todolist.presentation.reminder.components.ReminderScreen
import kotlinx.coroutines.Job

@Composable
fun ReminderRoute(
    navController: NavController,
    openDrawer: () -> Job
) {
    ReminderScreen(
        navController = navController,
        openDrawer = openDrawer
    )
}
