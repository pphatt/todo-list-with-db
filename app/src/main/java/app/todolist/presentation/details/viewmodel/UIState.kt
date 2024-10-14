package app.todolist.presentation.details.viewmodel

data class UIState(
    val content: String,

    val showDate: Boolean
) {
    companion object {
        val default = UIState(
            content = "",

            showDate = false
        )
    }
}
