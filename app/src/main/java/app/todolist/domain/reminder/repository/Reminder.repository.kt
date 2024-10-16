package app.todolist.domain.reminder.repository

import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.request.CreateReminderDto
import app.todolist.presentation.request.EditReminderDto
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ReminderRepository {
    suspend fun getAllReminders(): Flow<List<Reminder>>
    suspend fun getReminderById(id: UUID): Reminder?
    suspend fun createReminder(body: CreateReminderDto)
    suspend fun editReminder(body: EditReminderDto)
    suspend fun moveReminderToTrash(reminder: Reminder)
    suspend fun deleteReminder(id: String)
}
