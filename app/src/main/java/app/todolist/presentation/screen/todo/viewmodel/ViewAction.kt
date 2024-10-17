package app.todolist.presentation.screen.todo.viewmodel

sealed interface ViewAction {
    data object GetAllTodo : ViewAction
}
