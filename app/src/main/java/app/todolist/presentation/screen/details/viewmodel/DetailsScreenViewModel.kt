package app.todolist.presentation.screen.details.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todolist.domain.todo.entity.Todo
import app.todolist.infrastructure.repositories.TodoRepositoryImpl
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.EditTodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val todoRepositoryImpl: TodoRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState.default)
    val uiState = _uiState.asStateFlow()
    private var state: UIState
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    fun execute(action: ViewAction) {
        when (action) {
            is ViewAction.SetContent -> setContent(action.value)

            is ViewAction.SetShowDateTime -> setShowDateTime(action.value)

            ViewAction.ClearState -> clearState()
        }
    }

    private fun setContent(value: String) {
        state = state.copy(content = value)
    }

    private fun setShowDateTime(value: Boolean) {
        state = state.copy(showDate = value)
    }

    private fun clearState() {
        state = state.copy(
            content = UIState.default.content,
            showDate = UIState.default.showDate,
        )
    }

    fun getTodoById(id: Long) {
        viewModelScope.launch {
            val todo = todoRepositoryImpl.getTodoById(id)

            if (todo != null) {
                state = state.copy(
                    content = todo.content,
                    dueDate = todo.dueDate,
                    showDate = todo.dueDate != null
                )
            }
        }
    }

    fun createTodo(
        // NOTE: newTemporalTodoList currently is not a great solution,
        // but it solved it (show which one was the recently add when added).
        newTemporalTodoList: SnapshotStateList<Todo>,
        body: CreateTodoDto
    ) {
        viewModelScope.launch {
            val todo = todoRepositoryImpl.createTodo(body)
            newTemporalTodoList.add(todo)
        }
    }

    fun editTodo(body: EditTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.editTodo(body)
        }
    }
}
