package app.todolist.presentation.screen.reminder.viewmodel

import app.todolist.domain.reminder.entity.Reminder

data class UIState(
    val list: List<Reminder>,
) {
    companion object {
        val default = UIState(
            list = mutableListOf()
        )
    }
}
