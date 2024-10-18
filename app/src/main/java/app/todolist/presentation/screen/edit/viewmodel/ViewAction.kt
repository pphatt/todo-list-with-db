package app.todolist.presentation.screen.edit.viewmodel

import app.todolist.presentation.request.SoftDeleteTodoDto

sealed interface ViewAction {
    data class SetTodo(val id: Long) : ViewAction

    data class SoftDeleteTodo (val body: SoftDeleteTodoDto) : ViewAction

    data class DeleteTodo(val todoId: String) : ViewAction

    data class RestoreTodo(val todoId: String) : ViewAction
}
