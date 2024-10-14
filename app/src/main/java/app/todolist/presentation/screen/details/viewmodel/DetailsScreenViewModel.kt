package app.todolist.presentation.screen.details.viewmodel

import androidx.lifecycle.ViewModel
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.request.CreateReminderDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor() : ViewModel() {
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

    fun createReminder(list: MutableList<Reminder>, body: CreateReminderDto) {
        list.add(Reminder(content = body.content, dueDate = body.dueDate))
    }
}
