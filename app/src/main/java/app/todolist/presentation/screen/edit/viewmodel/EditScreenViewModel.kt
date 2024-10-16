package app.todolist.presentation.screen.edit.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.infrastructure.repositories.ReminderRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
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
            is ViewAction.SetReminder -> getReminderById(
                id = action.id
            )

            is ViewAction.MoveReminderToTrash -> moveReminderToTrash()

            is ViewAction.DeleteReminder -> deleteReminder(action.reminderId)
        }
    }

    private fun getReminderById(id: UUID) {
        viewModelScope.launch {
            reminderRepositoryImpl.getAllReminders().collect { reminders ->
                val reminder = reminders.find { reminder -> reminder.id == id }

                state = state.copy(reminder = reminder)
            }
        }
    }

    private fun moveReminderToTrash() {
        viewModelScope.launch {
            state.reminder?.let { reminderRepositoryImpl.moveReminderToTrash(it) }
        }
    }

    private fun deleteReminder(reminderId: String) {
        viewModelScope.launch {
            reminderRepositoryImpl.deleteReminder(reminderId)
        }
    }
}