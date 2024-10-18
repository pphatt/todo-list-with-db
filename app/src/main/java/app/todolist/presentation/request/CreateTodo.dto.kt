package app.todolist.presentation.request

data class CreateTodoDto(
    val content: String,
    val dueDate: Long?,
)
