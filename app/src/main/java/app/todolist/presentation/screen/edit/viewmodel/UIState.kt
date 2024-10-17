package app.todolist.presentation.screen.edit.viewmodel

import app.todolist.domain.todo.entity.Todo
import java.util.UUID

data class UIState(
    val id: UUID?,
    val todo: Todo?,
) {
    companion object {
        val default = UIState(
            id = null,
            todo = null
        )
    }
}
