package app.todolist.presentation.screen.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todolist.infrastructure.repositories.TodoRepositoryImpl
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.EditTodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
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

    fun getTodoById(id: String) {
        viewModelScope.launch {
            val todo = todoRepositoryImpl.getTodoById(UUID.fromString(id))

            if (todo != null) {
                state = state.copy(
                    content = todo.content,
                    dueDate = todo.dueDate,
                    showDate = todo.dueDate != null
                )
            }
        }
    }

    fun createTodo(body: CreateTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.createTodo(body)
        }
    }

    fun editTodo(body: EditTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.editTodo(body)
        }
    }
}
