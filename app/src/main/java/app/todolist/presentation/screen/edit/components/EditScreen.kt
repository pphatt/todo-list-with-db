package app.todolist.presentation.screen.edit.components

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.RestoreCompleteTodoDto
import app.todolist.presentation.request.RestoreTodoDto
import app.todolist.presentation.request.SoftDeleteTodoDto
import app.todolist.presentation.screen.edit.viewmodel.EditScreenViewModel
import app.todolist.presentation.screen.edit.viewmodel.ViewAction
import app.todolist.ui.theme.LocalColorScheme
import app.todolist.utils.convertMillisToDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(
    viewModel: EditScreenViewModel = hiltViewModel(),
    todoId: String?,
    isCurrentTrashRoute: Boolean = false,
    isCurrentCompleteRoute: Boolean = false,
    navigateToTodo: () -> Unit,
    navigateToTrash: () -> Unit,
    navigateToComplete: () -> Unit,
    navigateToEditDetails: (todoId: String?) -> Unit
) {
    val state = viewModel.uiState.collectAsState().value

    val scrollState = rememberScrollState()

    val context = LocalContext.current

    LaunchedEffect(todoId) {
        val id = todoId?.toLongOrNull()

        if (id != null) {
            viewModel.execute(ViewAction.SetTodo(id))
        }
    }

    val date = state.todo?.dueDate?.let { convertMillisToDate(it) } ?: "No date was set"

    Surface(
        modifier = Modifier.background(color = LocalColorScheme.current.primaryBackgroundColor)
    ) {
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
            containerColor = LocalColorScheme.current.primaryBackgroundColor,
            bottomBar = {
                if (isCurrentTrashRoute) {
                    AppBottomBarResume(
                        onRestoreTodo = {
                            if (todoId == null) {
                                return@AppBottomBarResume
                            }

                            viewModel.execute(
                                ViewAction.RestoreTodo(
                                    RestoreTodoDto(todoId.toLong())
                                )
                            )

                            Toast.makeText(
                                context,
                                "Restore todo successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            navigateToTrash()
                        },
                        onDeleteTodo = {
                            if (todoId == null) {
                                return@AppBottomBarResume
                            }

                            viewModel.execute(
                                ViewAction.DeleteTodo(
                                    DeleteTodoDto(id = todoId.toLong())
                                )
                            )

                            Toast.makeText(
                                context,
                                "Permanently delete todo successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            navigateToTrash()
                        }
                    )

                    return@Scaffold
                }

                if (isCurrentCompleteRoute) {
                    AppBottomBarResume(
                        onRestoreTodo = {
                            if (todoId == null) {
                                return@AppBottomBarResume
                            }

                            viewModel.execute(
                                ViewAction.RestoreCompleteTodo(
                                    RestoreCompleteTodoDto(todoId.toLong())
                                )
                            )

                            Toast.makeText(
                                context,
                                "Restore complete task successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            navigateToComplete()
                        },
                        onDeleteTodo = {
                            if (todoId == null) {
                                return@AppBottomBarResume
                            }

                            viewModel.execute(
                                ViewAction.SoftDeleteTodo(
                                    SoftDeleteTodoDto(
                                        id = todoId.toLong()
                                    )
                                )
                            )

                            Toast.makeText(
                                context,
                                "Delete task successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            navigateToComplete()
                        }
                    )

                    return@Scaffold
                }

                AppBottomBar(
                    onNavigateToEditTodo = { navigateToEditDetails(todoId) },
                    onDeleteTodo = {
                        if (todoId == null) {
                            return@AppBottomBar
                        }

                        viewModel.execute(
                            ViewAction.SoftDeleteTodo(
                                SoftDeleteTodoDto(
                                    id = todoId.toLong()
                                )
                            )
                        )

                        Toast.makeText(
                            context,
                            "Delete todo successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        navigateToTodo()
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
                TodoTextPlaceholder(
                    content = state.todo?.content ?: "",
                    isCurrentTrashRoute = isCurrentTrashRoute,
                    isCurrentCompleteRoute = isCurrentCompleteRoute,
                    onNavigateToEditTodo = { navigateToEditDetails(todoId) },
                    onNavigateBack = {
                        if (isCurrentTrashRoute)
                            navigateToTrash()
                        else if (isCurrentCompleteRoute)
                            navigateToComplete()
                        else navigateToTodo()
                    }
                )

                DatePlaceholder(date = date)
            }
        }
    }
}
