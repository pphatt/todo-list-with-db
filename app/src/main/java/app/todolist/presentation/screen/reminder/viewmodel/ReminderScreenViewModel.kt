package app.todolist.presentation.screen.reminder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todolist.domain.reminder.repository.ReminderRepository
import app.todolist.infrastructure.repositories.ReminderRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderScreenViewModel @Inject constructor(
    private val reminderRepositoryImpl: ReminderRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState.default)
    val uiState = _uiState.asStateFlow()
    private var state: UIState
        get() = _uiState.value
        set(value) {
            _uiState.update { value }
        }

    init {
        viewModelScope.launch {
            reminderRepositoryImpl.getAllReminders().collect { reminders ->
                state = state.copy(list = reminders)
            }
        }
    }
}
