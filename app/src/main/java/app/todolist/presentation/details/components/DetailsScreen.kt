package app.todolist.presentation.details.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.todolist.presentation.details.viewmodel.DetailsScreenViewModel
import app.todolist.presentation.details.viewmodel.ViewAction
import app.todolist.ui.navigation.NavigationActions
import app.todolist.ui.theme.LocalColorScheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navigationActions: NavigationActions
) {
    val state = viewModel.uiState.collectAsState().value

    val focusRequester = remember { FocusRequester() }

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.background(color = LocalColorScheme.current.primaryBackgroundColor)
    ) {
        Scaffold(
            containerColor = LocalColorScheme.current.primaryBackgroundColor,
            bottomBar = { BottomAppBarDefaults(navigationActions = navigationActions) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ReminderTextInput(
                    content = state.content,
                    onChange = { viewModel.execute(ViewAction.SetContent(it)) },
                    focusRequester = focusRequester,
                )

                Column(
                    modifier = Modifier
                        .wrapContentHeight(
                            align = Alignment.CenterVertically, unbounded = true
                        )
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color(0xFFfcfcfc)), verticalArrangement = Arrangement.Center
                ) {
                    DatePickerDocked()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
