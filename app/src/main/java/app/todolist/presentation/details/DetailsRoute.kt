package app.todolist.presentation.details

import androidx.compose.runtime.Composable
import app.todolist.presentation.details.components.DetailsScreen
import app.todolist.ui.navigation.NavigationActions

@Composable
fun DetailsRoute(navigationActions: NavigationActions) {
    DetailsScreen(navigationActions)
}