package app.todolist.presentation.screen.edit

import androidx.compose.runtime.Composable
import app.todolist.presentation.screen.edit.components.EditScreen

@Composable
fun EditRoute(
    todoId: String?,
    isCurrentTrashRoute: Boolean = false,
    navigateToTodo: () -> Unit,
    navigateToTrash: () -> Unit,
    navigateToEditDetails: (todoId: String?) -> Unit
) {
    EditScreen(
        todoId = todoId,
        isCurrentTrashRoute = isCurrentTrashRoute,
        navigateToTodo = navigateToTodo,
        navigateToTrash = navigateToTrash,
        navigateToEditDetails = navigateToEditDetails
    )
}
