package app.todolist.presentation.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.todolist.ui.navigation.NavigationActions
import app.todolist.ui.theme.LocalColorScheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navigationActions: NavigationActions) {
    Surface(
        modifier = Modifier
            .background(color = LocalColorScheme.current.primaryBackgroundColor)
    ) {
        Scaffold(
            containerColor = LocalColorScheme.current.primaryBackgroundColor,
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            Box() {
                Text("Details Screen")
            }
        }
    }
}
