package app.todolist.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.todolist.presentation.screen.details.DetailsRoute
import app.todolist.presentation.screen.reminder.ReminderRoute
import app.todolist.presentation.screen.share.ShareScreen
import app.todolist.presentation.screen.trash.TrashRoute
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
        composable(
            route = Tabs.REMINDER_ROUTE
        ) {
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
        composable(
            route = Tabs.TRASH_ROUTE
        ) {
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
        composable(
            route = Tabs.DETAILS_ROUTE,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(300),
                    initialOffset = { fullHeight -> fullHeight / 3 }
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    tween(300),
                    targetOffset = { fullHeight -> fullHeight / 3 }
                ) + fadeOut(animationSpec = tween(durationMillis = 200))
            }
        ) {
            DetailsRoute(
                navigationActions = navigationActions
            )
        }
    }
}
