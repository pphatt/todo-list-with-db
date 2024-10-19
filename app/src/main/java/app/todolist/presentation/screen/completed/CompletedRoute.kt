package app.todolist.presentation.screen.completed

import androidx.compose.runtime.Composable
import app.todolist.presentation.screen.completed.components.CompletedScreen
import kotlinx.coroutines.Job

@Composable
fun CompletedRoute(
    openDrawer: () -> Job,
    onEditTodoClick: (String) -> Unit
) {
    CompletedScreen(
        openDrawer = openDrawer,
        onEditTodoClick = onEditTodoClick
    )
}
