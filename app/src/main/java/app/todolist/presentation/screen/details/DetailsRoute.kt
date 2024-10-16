package app.todolist.presentation.screen.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.details.components.DetailsScreen

@Composable
fun DetailsRoute(
    reminderId: String? = null,
    navigateToReminder: () -> Unit,
    newTemporalRemindersList: SnapshotStateList<Reminder>
) {
    DetailsScreen(
        reminderId = reminderId,
        navigateToReminder = navigateToReminder,
        newTemporalRemindersList = newTemporalRemindersList
    )
}
