package app.todolist.presentation.details.viewmodel

import java.util.Date

sealed interface ViewAction {
    data class SetContent(val value: String) : ViewAction

    data class SetDatetime(val value: Date) : ViewAction
}
