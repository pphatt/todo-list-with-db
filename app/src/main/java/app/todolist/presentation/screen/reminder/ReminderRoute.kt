package app.todolist.presentation.screen.reminder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.infrastructure.repositories.ReminderRepositoryImpl
import app.todolist.presentation.screen.reminder.components.ReminderScreen
import kotlinx.coroutines.Job

@Composable
fun ReminderRoute(
    navController: NavController,
    openDrawer: () -> Job,
    newTemporalRemindersList: SnapshotStateList<Reminder>?
) {
    ReminderScreen(
        navController = navController,
        openDrawer = openDrawer,
        newTemporalRemindersList = newTemporalRemindersList
    )
}
