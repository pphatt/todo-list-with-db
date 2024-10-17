package app.todolist.presentation.screen.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.screen.details.components.DetailsScreen

@Composable
fun DetailsRoute(
    todoId: String? = null,
    navigateToTodo: () -> Unit,
    newTemporalTodoList: SnapshotStateList<Todo>
) {
    DetailsScreen(
        todoId = todoId,
        navigateToTodo = navigateToTodo,
        newTemporalTodoList = newTemporalTodoList
    )
}
