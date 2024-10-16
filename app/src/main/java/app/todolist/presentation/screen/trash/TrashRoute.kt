package app.todolist.presentation.screen.trash

import androidx.compose.runtime.Composable
import app.todolist.presentation.screen.trash.components.TrashScreen
import kotlinx.coroutines.Job

@Composable
fun TrashRoute(
    openDrawer: () -> Job,
) {
    TrashScreen(openDrawer = openDrawer)
}
