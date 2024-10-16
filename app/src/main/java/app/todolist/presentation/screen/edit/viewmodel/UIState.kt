package app.todolist.presentation.screen.edit.viewmodel

import app.todolist.domain.reminder.entity.Reminder
import java.util.UUID

data class UIState(
    val id: UUID?,
    val reminder: Reminder?,
) {
    companion object {
        val default = UIState(
            id = null,
            reminder = null
        )
    }
}
