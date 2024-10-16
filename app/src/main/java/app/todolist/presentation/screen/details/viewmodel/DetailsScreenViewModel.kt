package app.todolist.presentation.screen.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.domain.reminder.repository.ReminderRepository
import app.todolist.infrastructure.repositories.ReminderRepositoryImpl
import app.todolist.presentation.request.CreateReminderDto
import app.todolist.presentation.request.EditReminderDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val reminderRepositoryImpl: ReminderRepositoryImpl
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

    fun getReminderById(id: String) {
        viewModelScope.launch {
            val reminder = reminderRepositoryImpl.getReminderById(UUID.fromString(id))

            if (reminder != null) {
                state = state.copy(
                    content = reminder.content,
                    dueDate = reminder.dueDate,
                    showDate = reminder.dueDate != null
                )
            }
        }
    }

    fun createReminder(body: CreateReminderDto) {
        viewModelScope.launch {
            reminderRepositoryImpl.createReminder(body)
        }
    }

    fun editReminder(body: EditReminderDto) {
        println("body: $body")
        viewModelScope.launch {
            reminderRepositoryImpl.editReminder(body)
        }
    }
}
