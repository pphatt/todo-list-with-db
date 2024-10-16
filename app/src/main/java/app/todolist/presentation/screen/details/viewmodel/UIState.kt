package app.todolist.presentation.screen.details.viewmodel

data class UIState(
    val content: String,
    val dueDate: Long?,

    val showDate: Boolean
) {
    companion object {
        val default = UIState(
            content = "",
            dueDate = null,

            showDate = false
        )
    }
}
