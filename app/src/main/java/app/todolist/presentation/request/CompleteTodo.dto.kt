package app.todolist.presentation.request

data class CompleteTodoDto(
    val id: Long,
    val completedAt: Long,
)
