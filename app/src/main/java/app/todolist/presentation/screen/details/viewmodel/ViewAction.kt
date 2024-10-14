package app.todolist.presentation.screen.details.viewmodel

import java.util.Date

sealed interface ViewAction {
    data class SetContent(val value: String) : ViewAction

    data object ClearState : ViewAction

    data class SetShowDateTime(val value: Boolean) : ViewAction
}
