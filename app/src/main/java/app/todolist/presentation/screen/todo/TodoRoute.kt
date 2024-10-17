package app.todolist.presentation.screen.todo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.screen.todo.components.TodoScreen
import kotlinx.coroutines.Job

@Composable
fun TodoRoute(
    navigateToDetails: () -> Unit,
    openDrawer: () -> Job,
    temporalTodoList: SnapshotStateList<Todo>?,
    onTodoClick: (Todo) -> Unit
) {
    TodoScreen(
        navigateToDetails = navigateToDetails,
        openDrawer = openDrawer,
        temporalTodos = temporalTodoList,
        onTodoClick = onTodoClick
    )
}
