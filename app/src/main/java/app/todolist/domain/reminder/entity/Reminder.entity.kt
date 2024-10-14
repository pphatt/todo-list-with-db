package app.todolist.domain.reminder.entity

import java.util.UUID

data class Reminder(
    val id: UUID = UUID.randomUUID(),
    val content: String,
    val dueDate: Long?,
)

class InvalidNoteException(message: String) : Exception(message)
