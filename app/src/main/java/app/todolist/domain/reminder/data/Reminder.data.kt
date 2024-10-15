package app.todolist.domain.reminder.data

import app.todolist.domain.reminder.entity.Reminder
import java.sql.Timestamp
import java.util.UUID

data class ReminderInitialData(val list: List<Reminder>) {
    companion object {
        val default = listOf(
            Reminder(
                UUID.randomUUID(),
                "11",
                1728549289000,
                Timestamp.valueOf("2024-10-15 15:20:51.898")
            ),
            Reminder(
                UUID.randomUUID(),
                "22",
                1728462889000,
                Timestamp.valueOf("2024-10-15 15:20:54.547")
            ),
            Reminder(
                UUID.randomUUID(),
                "33",
                1730363689000,
                Timestamp.valueOf("2024-10-15 15:20:56.712")
            ),
            Reminder(
                UUID.randomUUID(),
                "44",
                1729154089000,
                Timestamp.valueOf("2024-10-15 15:20:58.723")
            ),
            Reminder(
                UUID.randomUUID(),
                "55",
                null,
                Timestamp.valueOf("2024-10-15 15:21:00.698")
            ),
            Reminder(
                UUID.randomUUID(),
                "66",
                null,
                Timestamp.valueOf("2024-10-15 15:21:02.974")
            ),
            Reminder(
                UUID.randomUUID(),
                "77",
                null,
                Timestamp.valueOf("2024-10-15 15:21:07.21")
            ),
            Reminder(
                UUID.randomUUID(),
                "88",
                1728950400000,
                Timestamp.valueOf("2024-10-15 15:21:10.486")
            ),
            Reminder(
                UUID.randomUUID(),
                "ygfr",
                1729382400000,
                Timestamp.valueOf("2024-10-15 15:21:16.449")
            ),
            Reminder(
                UUID.randomUUID(),
                "fdfg",
                null,
                Timestamp.valueOf("2024-10-15 15:21:19.18")
            ),
            Reminder(
                UUID.randomUUID(),
                "ppp",
                null,
                Timestamp.valueOf("2024-10-15 15:21:21.752")
            )
        )
    }
}