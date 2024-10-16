package app.todolist.presentation.screen.edit

import androidx.compose.runtime.Composable
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.edit.components.EditScreen

@Composable
fun EditRoute(
    reminderId: String?
) {
    EditScreen(reminderId = reminderId)
}
