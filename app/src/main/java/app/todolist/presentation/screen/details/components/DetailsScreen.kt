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
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.request.CreateReminderDto
import app.todolist.presentation.request.EditReminderDto
import app.todolist.presentation.screen.details.viewmodel.DetailsScreenViewModel
import app.todolist.presentation.screen.details.viewmodel.ViewAction
import app.todolist.ui.theme.LocalColorScheme
import app.todolist.utils.PresentOrFutureSelectableDates
import app.todolist.utils.getCurrentDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    reminderId: String? = null,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navigateToReminder: () -> Unit,
    newTemporalRemindersList: SnapshotStateList<Reminder>
) {
    val state = viewModel.uiState.collectAsState().value

    // cannot move it to viewmodel
    // TODO: fix this (this can cause memory leak)
    var datePickerState: DatePickerState? = null

    if (reminderId == null) {
        datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = getCurrentDateTime().time,
            selectableDates = PresentOrFutureSelectableDates
        )
    } else if (state.dueDate != null) {
        datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = state.dueDate,
            selectableDates = PresentOrFutureSelectableDates
        )
    }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    LaunchedEffect(reminderId) {
        if (reminderId != null) {
            viewModel.getReminderById(reminderId)
        }
    }

    Surface(
        modifier = Modifier.background(color = LocalColorScheme.current.primaryBackgroundColor)
    ) {
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
            containerColor = LocalColorScheme.current.primaryBackgroundColor,
            bottomBar = {
                AppBottomBar(
                    content = state.content,
                    onExitReminder = {
                        focusManager.clearFocus()

                        viewModel.execute(ViewAction.ClearState)

                        navigateToReminder()
                    },
                    onSaveReminder = {
                        focusManager.clearFocus()

                        // TODO: change this back dto when using db
                        val reminder = Reminder(
                            content = state.content.trim(),
                            dueDate = if (state.showDate) datePickerState?.selectedDateMillis else null
                        )

                        if (reminderId == null) {
                            viewModel.createReminder(
                                CreateReminderDto(
                                    id = reminder.id,
                                    content = state.content.trim(),
                                    dueDate = if (state.showDate) datePickerState?.selectedDateMillis else null,
                                    createdAt = reminder.createdAt
                                )
                            )

                            newTemporalRemindersList.add(reminder)
                        } else {
                            viewModel.editReminder(
                                EditReminderDto(
                                    id = UUID.fromString(reminderId),
                                    content = state.content.trim(),
                                    dueDate = if (state.showDate) datePickerState?.selectedDateMillis else null,
                                    createdAt = reminder.createdAt
                                )
                            )
                        }

                        navigateToReminder()

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

                if (datePickerState != null) {
                    DatePickerDocked(
                        date = datePickerState,
                        showDatePicker = state.showDate,
                        onToggleShowDatePicker = { viewModel.execute(ViewAction.SetShowDateTime(!state.showDate)) }
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
