package app.todolist.infrastructure.repositories

import app.todolist.domain.reminder.data.ReminderInitialData
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.domain.reminder.repository.ReminderRepository
import app.todolist.presentation.request.CreateReminderDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRepositoryImpl @Inject constructor() : ReminderRepository {
    private val remindersList = MutableStateFlow(
        mutableListOf(
            *ReminderInitialData.default.toTypedArray()
        )
    )

    override suspend fun getAllReminders(): Flow<List<Reminder>> {
        return remindersList
    }

    override suspend fun getReminderById(id: UUID): Reminder? {
        return remindersList.value.find { it.id == id }
    }

    override suspend fun createReminder(body: CreateReminderDto) {
        val newReminder = Reminder(
            content = body.content,
            dueDate = body.dueDate
        )

        remindersList.value.add(newReminder)
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        remindersList.value = remindersList.value.toMutableList().apply {
            remove(reminder)
        }
    }
}
