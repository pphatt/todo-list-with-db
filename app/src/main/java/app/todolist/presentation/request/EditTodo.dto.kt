package app.todolist.presentation.request

data class EditTodoDto(
    val id: Long,
    val content: String,
    val dueDate: Long?,
)
