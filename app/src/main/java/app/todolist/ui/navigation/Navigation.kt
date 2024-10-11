package app.todolist.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Destinations used in the [AppNavigation].
 */
object Tabs {
    const val REMINDER_ROUTE = "Reminders"
    const val TRASH_ROUTE = "Trash"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(navHostController: NavHostController) {
    val navigateToReminder: () -> Unit = {
        navHostController.navigate(Tabs.REMINDER_ROUTE) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToTrash: () -> Unit = {
        navHostController.navigate(Tabs.TRASH_ROUTE) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
