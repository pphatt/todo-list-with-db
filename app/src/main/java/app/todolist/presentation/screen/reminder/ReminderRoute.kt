package app.todolist.presentation.screen.reminder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.reminder.components.ReminderScreen
import kotlinx.coroutines.Job

@Composable
fun ReminderRoute(
    navigateToDetails: () -> Unit,
    openDrawer: () -> Job,
    temporalRemindersList: SnapshotStateList<Reminder>?,
    onReminderClick: (Reminder) -> Unit
) {
    ReminderScreen(
        navigateToDetails = navigateToDetails,
        openDrawer = openDrawer,
        temporalReminders = temporalRemindersList,
        onReminderClick = onReminderClick
    )
}
