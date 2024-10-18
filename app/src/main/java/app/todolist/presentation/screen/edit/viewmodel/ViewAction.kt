package app.todolist.presentation.screen.edit.viewmodel

sealed interface ViewAction {
    data class SetTodo(val id: Long) : ViewAction

    data object MoveTodoToTrash : ViewAction

    data class DeleteTodo(val todoId: String) : ViewAction

    data class RestoreTodo(val todoId: String) : ViewAction
}
