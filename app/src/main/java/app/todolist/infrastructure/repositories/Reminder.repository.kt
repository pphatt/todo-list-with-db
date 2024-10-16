package app.todolist.infrastructure.repositories

import app.todolist.domain.reminder.data.ReminderInitialData
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.domain.reminder.repository.ReminderRepository
import app.todolist.presentation.request.CreateReminderDto
import app.todolist.presentation.request.EditReminderDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.sql.Timestamp
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
            createdAt = body.createdAt
        )

        remindersList.value.add(newReminder)
    }

    override suspend fun editReminder(body: EditReminderDto) {
        val reminderIndex = remindersList.value.indexOfFirst { it.id == body.id }

        println(remindersList.value)
        println(reminderIndex)

        if (reminderIndex != -1) {
            val updatedReminder = Reminder(
                id = body.id,
                content = body.content,
                dueDate = body.dueDate,
                createdAt = body.createdAt
            )

            println("updatedReminder: $updatedReminder")

            val updatedList = remindersList.value.toMutableList()
            updatedList[reminderIndex] = updatedReminder

            remindersList.value = updatedList
        }
    }

    override suspend fun moveReminderToTrash(reminder: Reminder) {
        val updatedReminders = remindersList.value.toMutableList().apply {
            val index = indexOfFirst { it.id == reminder.id }

            if (index != -1) {
                val updatedReminder =
                    reminder.copy(deletedAt = Timestamp(System.currentTimeMillis()))
                set(index, updatedReminder)
            }
        }

        remindersList.value = updatedReminders
    }

    override suspend fun deleteReminder(id: String) {
        val reminderToDelete = getReminderById(UUID.fromString(id))

        reminderToDelete?.let { reminder ->
            remindersList.value = remindersList.value.toMutableList().apply {
                remove(reminder)
            }
        }
    }
}
