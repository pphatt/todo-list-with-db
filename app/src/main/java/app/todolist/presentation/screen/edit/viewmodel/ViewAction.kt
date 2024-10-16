package app.todolist.presentation.screen.edit.viewmodel

import app.todolist.domain.reminder.entity.Reminder
import java.util.UUID

sealed interface ViewAction {
    data class SetId(val value: UUID) : ViewAction

    data class SetReminder(val id: UUID) : ViewAction
}
