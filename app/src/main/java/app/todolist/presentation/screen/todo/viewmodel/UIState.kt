package app.todolist.presentation.screen.todo.viewmodel

import app.todolist.domain.todo.entity.Todo

data class UIState(
    val list: List<Todo>,
) {
    companion object {
        val default = UIState(
            list = mutableListOf()
        )
    }
}
