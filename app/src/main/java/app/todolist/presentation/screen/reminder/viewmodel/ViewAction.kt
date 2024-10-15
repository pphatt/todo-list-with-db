package app.todolist.presentation.screen.reminder.viewmodel

sealed interface ViewAction {
    data object GetAllReminders : ViewAction
}
