package app.todolist.presentation.screen.reminder

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.reminder.components.ReminderScreen
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
