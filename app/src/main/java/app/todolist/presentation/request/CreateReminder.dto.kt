package app.todolist.presentation.request

data class CreateReminderDto(
    val content: String,
    val dueDate: Long?
)
