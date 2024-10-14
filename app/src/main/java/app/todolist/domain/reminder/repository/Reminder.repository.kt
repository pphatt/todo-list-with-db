package app.todolist.domain.reminder.repository

import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.request.CreateReminderDto
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    suspend fun getAllReminders(): Flow<List<Reminder>>
    suspend fun getReminderById(id: Int): Reminder?
    suspend fun createReminder(reminder: CreateReminderDto)
    suspend fun deleteReminder(reminder: Reminder)
}
