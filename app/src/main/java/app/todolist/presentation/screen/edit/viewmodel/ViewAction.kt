package app.todolist.presentation.screen.edit.viewmodel

import app.todolist.domain.reminder.entity.Reminder
import java.util.UUID

sealed interface ViewAction {
    data class SetReminder(val id: UUID) : ViewAction

    data object DeleteReminder : ViewAction
}
