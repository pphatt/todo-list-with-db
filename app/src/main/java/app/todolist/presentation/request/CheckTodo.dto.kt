package app.todolist.presentation.request

data class CheckTodoDto(
    val id: Long,
    val status: Boolean,
)
