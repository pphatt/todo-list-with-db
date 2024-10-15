package app.todolist.domain.reminder.entity

import java.sql.Timestamp
import java.util.UUID

data class Reminder(
    val id: UUID = UUID.randomUUID(),
    val content: String,
    val dueDate: Long? = null,
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis())
)

class InvalidNoteException(message: String) : Exception(message)
