package app.todolist.presentation.screen.edit

import androidx.compose.runtime.Composable
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.edit.components.EditScreen

@Composable
fun EditRoute(
    reminderId: String?,
    navigateToReminder: () -> Unit,
    navigateToEditDetails: (reminderId: String?) -> Unit
) {
    EditScreen(
        reminderId = reminderId,
        navigateToReminder = navigateToReminder,
        navigateToEditDetails = navigateToEditDetails
    )
}
