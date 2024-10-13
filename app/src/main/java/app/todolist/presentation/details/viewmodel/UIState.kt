package app.todolist.presentation.details.viewmodel

data class UIState(
    val content: String,

    val date: Date
) {
    companion object {
        val default = UIState(
            content = "",

            date = currentDate(),
        )
    }
}
