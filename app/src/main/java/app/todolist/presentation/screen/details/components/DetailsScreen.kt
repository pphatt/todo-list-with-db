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
import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.EditTodoDto
import app.todolist.presentation.screen.details.viewmodel.DetailsScreenViewModel
import app.todolist.presentation.screen.details.viewmodel.ViewAction
import app.todolist.ui.theme.LocalColorScheme
import app.todolist.utils.PresentOrFutureSelectableDates
import app.todolist.utils.getCurrentDateTime
import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    todoId: String? = null,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    navigateToTodo: () -> Unit,
    newTemporalTodoList: SnapshotStateList<Todo>
) {
    val state = viewModel.uiState.collectAsState().value

    // cannot move it to viewmodel
    // TODO: fix this (this can cause memory leak)
    val datePickerState: DatePickerState =
        if (todoId == null || state.dueDate == null) {
            rememberDatePickerState(
                initialSelectedDateMillis = getCurrentDateTime(),
                selectableDates = PresentOrFutureSelectableDates
            )
        } else {
            rememberDatePickerState(
                initialSelectedDateMillis = state.dueDate,
                selectableDates = PresentOrFutureSelectableDates
            )
        }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val scrollState = rememberScrollState()

    LaunchedEffect(todoId) {
        if (todoId != null) {
            viewModel.getTodoById(todoId)
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
                    onExitTodo = {
                        focusManager.clearFocus()

                        viewModel.execute(ViewAction.ClearState)

                        navigateToTodo()
                    },
                    onSaveTodo = {
                        focusManager.clearFocus()

                        // TODO: change this back dto when using db
                        val todo = Todo(
                            content = state.content.trim(),
                            dueDate = if (state.showDate) datePickerState.selectedDateMillis else null
                        )

                        if (todoId == null) {
                            viewModel.createTodo(
                                CreateTodoDto(
                                    id = todo.id,
                                    content = state.content.trim(),
                                    dueDate = if (state.showDate) datePickerState.selectedDateMillis else null,
                                    createdAt = todo.createdAt
                                )
                            )

                            newTemporalTodoList.add(todo)
                        } else {
                            viewModel.editTodo(
                                EditTodoDto(
                                    id = UUID.fromString(todoId),
                                    content = state.content.trim(),
                                    dueDate = if (state.showDate) datePickerState.selectedDateMillis else null,
                                    createdAt = todo.createdAt
                                )
                            )
                        }

                        navigateToTodo()

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
                TodoTextInput(
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
