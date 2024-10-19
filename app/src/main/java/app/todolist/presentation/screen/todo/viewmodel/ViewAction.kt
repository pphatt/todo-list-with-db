package app.todolist.presentation.screen.todo.viewmodel

import app.todolist.presentation.request.CheckTodoDto

sealed interface ViewAction {
    data class CheckTodo(val body: CheckTodoDto) : ViewAction
}
