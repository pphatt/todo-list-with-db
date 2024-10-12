package app.todolist.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.todolist.ui.component.AppDrawer
import app.todolist.ui.navigation.NavigationActions
import app.todolist.ui.navigation.NavigationGraph
import app.todolist.ui.navigation.Tabs
import app.todolist.ui.theme.LocalColorScheme
import kotlinx.coroutines.launch

@Composable
fun MainActivityScreen(
    widthSizeClass: WindowWidthSizeClass,
) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }

    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: Tabs.REMINDER_ROUTE

    // comment out for some internal design not finished
    // val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
    val isExpandedScreen = false

    val drawerState = rememberSizeAwareDrawerState(isExpandedScreen)

    val openDrawer = { coroutineScope.launch { drawerState.open() } }
    val closeDrawer = { coroutineScope.launch { drawerState.close() } }

    NavigationGraph(
        navController = navController,
        navigationActions = navigationActions,

        currentRoute = currentRoute,

        drawerState = drawerState,
        openDrawer = openDrawer,
        closeDrawer = closeDrawer,
    )
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}
