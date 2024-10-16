package app.todolist.presentation.screen.edit.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.todolist.presentation.screen.edit.viewmodel.EditScreenViewModel
import app.todolist.presentation.screen.edit.viewmodel.ViewAction
import app.todolist.ui.theme.LocalColorScheme
import app.todolist.utils.convertMillisToDate
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(
    viewModel: EditScreenViewModel = hiltViewModel(),
    reminderId: String?
) {
    val state = viewModel.uiState.collectAsState().value

    val scrollState = rememberScrollState()

    LaunchedEffect(reminderId) {
        viewModel.execute(ViewAction.SetReminder(UUID.fromString(reminderId)))
    }

    val date = state.reminder?.dueDate?.let { convertMillisToDate(it) } ?: "No date was set"

    Surface(
        modifier = Modifier.background(color = LocalColorScheme.current.primaryBackgroundColor)
    ) {
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
            containerColor = LocalColorScheme.current.primaryBackgroundColor,
            bottomBar = { AppBottomBar() }
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .navigationBarsPadding()
                    .fillMaxSize()
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ReminderTextPlaceholder(content = state.reminder?.content ?: "")

                DatePlaceholder(date = date)
            }
        }
    }
}
