package app.todolist.presentation.screen.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.details.components.DetailsScreen
import app.todolist.ui.navigation.NavigationActions

@Composable
fun DetailsRoute(
    navigateToReminder: () -> Unit,
    newTemporalRemindersList: SnapshotStateList<Reminder>
) {
    DetailsScreen(
        navigateToReminder = navigateToReminder,
        newTemporalRemindersList = newTemporalRemindersList
    )
}
