package app.todolist.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.details.DetailsRoute
import app.todolist.presentation.screen.edit.EditRoute
import app.todolist.presentation.screen.reminder.ReminderRoute
import app.todolist.presentation.screen.share.ShareScreen
import app.todolist.presentation.screen.trash.TrashRoute
import kotlinx.coroutines.Job

const val REMINDER_ID = "reminderId"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions,
    startDestination: String = Tabs.REMINDER_ROUTE,

    drawerState: DrawerState,
    currentRoute: String,

    temporalRemindersList: SnapshotStateList<Reminder>,

    openDrawer: () -> Job,
    closeDrawer: () -> Job
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Tabs.REMINDER_ROUTE,
            enterTransition = {
                fadeIn(animationSpec = tween(durationMillis = 0))
            },
        ) {
            ShareScreen(
                navigationActions = navigationActions,
                drawerState = drawerState,
                currentRoute = currentRoute,
                closeDrawer = closeDrawer,
                content = {
                    ReminderRoute(
                        navigateToDetails = navigationActions.navigateToDetails,
                        openDrawer = openDrawer,
                        temporalRemindersList = temporalRemindersList,
                        onReminderClick = { reminder ->
                            val reminderId = reminder.id.toString()
                            navigationActions.navigateToEdit(reminderId)
                        }
                    )
                }
            )
        }
        composable(
            route = Tabs.TRASH_ROUTE,
            enterTransition = {
                fadeIn(animationSpec = tween(durationMillis = 0))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 0))
            }
        ) {
            ShareScreen(
                navigationActions = navigationActions,
                drawerState = drawerState,
                currentRoute = currentRoute,
                closeDrawer = closeDrawer,
                content = {
                    TrashRoute(
                        openDrawer = openDrawer,
                    )
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
                navigateToReminder = { navigationActions.navigateToReminder() },
                newTemporalRemindersList = temporalRemindersList
            )
        }
        composable(
            route = "${Tabs.DETAILS_ROUTE}/{${REMINDER_ID}}",
            arguments = listOf(
                navArgument("reminderId") {
                    type = NavType.StringType
                }
            ),
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
        ) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getString("reminderId")
            println(reminderId)

            DetailsRoute(
                reminderId = reminderId,
                navigateToReminder = { navigationActions.navigateToReminder() },
                newTemporalRemindersList = temporalRemindersList
            )
        }
        composable(
            route = "${Tabs.EDIT_ROUTE}/{${REMINDER_ID}}",
            arguments = listOf(
                navArgument("reminderId") {
                    type = NavType.StringType
                }
            ),
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
        ) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getString("reminderId")

            EditRoute(
                reminderId = reminderId,
                navigateToReminder = { navigationActions.navigateToReminder() },
                navigateToEditDetails = { navigationActions.navigateToEditDetails(reminderId) }
            )
        }
    }
}
