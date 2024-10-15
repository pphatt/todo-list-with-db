package app.todolist.presentation.screen.details.components

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
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.todolist.presentation.request.CreateReminderDto
import app.todolist.presentation.screen.details.viewmodel.DetailsScreenViewModel
import app.todolist.presentation.screen.details.viewmodel.ViewAction
import app.todolist.ui.navigation.NavigationActions
import app.todolist.ui.theme.LocalColorScheme
import app.todolist.utils.PresentOrFutureSelectableDates
import app.todolist.utils.getCurrentDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navigationActions: NavigationActions
) {
    val state = viewModel.uiState.collectAsState().value

    // cannot move it to viewmodel
    val datePickerState =
        rememberDatePickerState(
            initialSelectedDateMillis = getCurrentDateTime().time,
            selectableDates = PresentOrFutureSelectableDates
        )

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.background(color = LocalColorScheme.current.primaryBackgroundColor)
    ) {
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
            containerColor = LocalColorScheme.current.primaryBackgroundColor,
            bottomBar = {
                BottomAppBarDefaults(
                    content = state.content,
                    onExitReminder = {
                        focusManager.clearFocus()
                        navigationActions.navigateToReminder()
                        viewModel.execute(ViewAction.ClearState)
                    },
                    onSaveReminder = {
                        focusManager.clearFocus()

                        viewModel.createReminder(
                            CreateReminderDto(
                                content = state.content.trim(),
                                dueDate = if (state.showDate) datePickerState.selectedDateMillis else null
                            )
                        )

                        navigationActions.navigateToReminder()

                        viewModel.execute(ViewAction.ClearState)
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .navigationBarsPadding()
                    .fillMaxSize()
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ReminderTextInput(
                    content = state.content,
                    onChange = { viewModel.execute(ViewAction.SetContent(it)) },
                    focusRequester = focusRequester,
                )

                DatePickerDocked(
                    date = datePickerState,
                    showDatePicker = state.showDate,
                    onToggleShowDatePicker = { viewModel.execute(ViewAction.SetShowDateTime(!state.showDate)) }
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
