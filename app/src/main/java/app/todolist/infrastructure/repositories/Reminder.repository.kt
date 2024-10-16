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
        // TODO: change this back dto when using db
        val newReminder = Reminder(
            id = body.id,
            content = body.content,
            dueDate = body.dueDate,
            timestamp = body.timestamp
        )

        remindersList.value.add(newReminder)
    }

    override suspend fun deleteReminder(reminder: Reminder) {
        val updatedReminders = remindersList.value.toMutableList().apply {
            val index = indexOfFirst { it.id == reminder.id }

            if (index != -1) {
                // Update the reminder's isDeleted property
                val updatedReminder = reminder.copy(isDeleted = true)
                set(index, updatedReminder)
            }
        }

        remindersList.value = updatedReminders
    }
}
