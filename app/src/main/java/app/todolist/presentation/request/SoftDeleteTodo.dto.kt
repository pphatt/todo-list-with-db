package app.todolist.presentation.request

data class SoftDeleteTodoDto(
    val id: Long,
    val deletedAt: Long = System.currentTimeMillis(),
)
