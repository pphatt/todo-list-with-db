package app.todolist.presentation.screen.edit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todolist.infrastructure.repositories.TodoRepositoryImpl
import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.RestoreCompleteTodoDto
import app.todolist.presentation.request.RestoreTodoDto
import app.todolist.presentation.request.SoftDeleteTodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
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
            is ViewAction.SetTodo -> getTodoById(
                id = action.id
            )

            is ViewAction.SoftDeleteTodo -> softDeleteTodo(action.body)

            is ViewAction.DeleteTodo -> deleteTodo(action.body)

            is ViewAction.RestoreTodo -> restoreTodo(action.body)

            is ViewAction.RestoreCompleteTodo -> restoreCompleteTodo(action.body)
        }
    }

    private fun getTodoById(id: Long) {
        viewModelScope.launch {
            todoRepositoryImpl.getAllTodo().collect { todoList ->
                val todo = todoList.find { todo -> todo.id == id }
                state = state.copy(todo = todo)
            }
        }
    }

    private fun softDeleteTodo(body: SoftDeleteTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.softDeleteTodo(body)
        }
    }

    private fun deleteTodo(body: DeleteTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.deleteTodo(body)
        }
    }

    private fun restoreTodo(body: RestoreTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.restoreTodo(body)
        }
    }

    private fun restoreCompleteTodo(body: RestoreCompleteTodoDto) {
        viewModelScope.launch {
            todoRepositoryImpl.restoreCompleteTodo(body)
        }
    }
}
