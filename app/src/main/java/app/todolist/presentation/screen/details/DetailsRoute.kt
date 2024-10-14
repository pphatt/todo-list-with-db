package app.todolist.presentation.screen.details

import androidx.compose.runtime.Composable
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.details.components.DetailsScreen
import app.todolist.ui.navigation.NavigationActions

@Composable
fun DetailsRoute(
    navigationActions: NavigationActions
) {
    DetailsScreen(
        navigationActions = navigationActions
    )
}
