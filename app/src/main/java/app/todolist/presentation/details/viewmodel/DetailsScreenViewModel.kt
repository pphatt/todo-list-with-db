package app.todolist.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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

            is ViewAction.SetDatetime -> setDatetime(action.value)
        }
    }

    private fun setContent(value: String) {
        state = state.copy(content = value)
    }

    private fun setDatetime(value: Date) {
        state = state.copy(date = value)
    }

    private fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

        return formatter.format(Date(millis))
    }
}
