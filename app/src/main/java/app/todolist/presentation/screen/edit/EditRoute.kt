package app.todolist.presentation.screen.edit

import androidx.compose.runtime.Composable
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.edit.components.EditScreen

@Composable
fun EditRoute(
    reminderId: String?,
    isCurrentTrashRoute: Boolean = false,
    navigateToReminder: () -> Unit,
    navigateToTrash: () -> Unit,
    navigateToEditDetails: (reminderId: String?) -> Unit
) {
    EditScreen(
        reminderId = reminderId,
        isCurrentTrashRoute = isCurrentTrashRoute,
        navigateToReminder = navigateToReminder,
        navigateToTrash = navigateToTrash,
        navigateToEditDetails = navigateToEditDetails
    )
}
