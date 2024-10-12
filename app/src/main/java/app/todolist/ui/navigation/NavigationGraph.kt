package app.todolist.ui.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.todolist.presentation.details.DetailsRoute
import app.todolist.presentation.reminder.ReminderRoute
import app.todolist.presentation.reminder.components.ReminderScreen
import app.todolist.presentation.share.ShareScreen
import app.todolist.presentation.trash.TrashRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions,
    startDestination: String = Tabs.REMINDER_ROUTE,

    drawerState: DrawerState,
    currentRoute: String,

    openDrawer: () -> Job,
    closeDrawer: () -> Job
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Tabs.REMINDER_ROUTE) {
            ShareScreen(
                navigationActions = navigationActions,
                drawerState = drawerState,
                currentRoute = currentRoute,
                closeDrawer = closeDrawer,
                content = {
                    ReminderRoute(
                        navController = navController,
                        openDrawer = openDrawer
                    )
                }
            )
        }
        composable(route = Tabs.TRASH_ROUTE) {
            ShareScreen(
                navigationActions = navigationActions,
                drawerState = drawerState,
                currentRoute = currentRoute,
                closeDrawer = closeDrawer,
                content = {
                    TrashRoute()
                }
            )
        }
        composable(route = Tabs.DETAILS_ROUTE) {
            DetailsRoute(navigationActions)
        }
    }
}
