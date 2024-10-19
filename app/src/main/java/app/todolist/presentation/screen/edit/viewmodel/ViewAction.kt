package app.todolist.presentation.screen.edit.viewmodel

import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.RestoreCompleteTodoDto
import app.todolist.presentation.request.RestoreTodoDto
import app.todolist.presentation.request.SoftDeleteTodoDto

sealed interface ViewAction {
    data class SetTodo(val id: Long) : ViewAction

    data class SoftDeleteTodo(val body: SoftDeleteTodoDto) : ViewAction

    data class DeleteTodo(val body: DeleteTodoDto) : ViewAction

    data class RestoreTodo(val body: RestoreTodoDto) : ViewAction

    data class RestoreCompleteTodo(val body: RestoreCompleteTodoDto) : ViewAction
}
