package app.todolist.presentation.request

import java.sql.Timestamp
import java.util.UUID

// TODO: change this back dto when using db
data class CreateTodoDto(
    val id: UUID,
    val content: String,
    val dueDate: Long?,
    val createdAt: Timestamp
)
