package app.todolist.domain.todo.data

import app.todolist.domain.todo.entity.Todo
import java.sql.Timestamp
import java.util.UUID

data class TodoInitialData(val list: List<Todo>) {
    companion object {
        val default = listOf(
            Todo(
                UUID.randomUUID(),
                "11",
                1728549289000,
                Timestamp.valueOf("2024-10-15 15:20:51.898")
            ),
            Todo(
                UUID.randomUUID(),
                "22",
                1728462889000,
                Timestamp.valueOf("2024-10-15 15:20:54.547")
            ),
            Todo(
                UUID.randomUUID(),
                "33",
                1730363689000,
                Timestamp.valueOf("2024-10-15 15:20:56.712")
            ),
            Todo(
                UUID.randomUUID(),
                "44",
                1729154089000,
                Timestamp.valueOf("2024-10-15 15:20:58.723")
            ),
            Todo(
                UUID.randomUUID(),
                "55",
                null,
                Timestamp.valueOf("2024-10-15 15:21:00.698")
            ),
            Todo(
                UUID.randomUUID(),
                "66",
                null,
                Timestamp.valueOf("2024-10-15 15:21:02.974")
            ),
            Todo(
                UUID.randomUUID(),
                "77",
                null,
                Timestamp.valueOf("2024-10-15 15:21:07.21")
            ),
            Todo(
                UUID.randomUUID(),
                "88",
                1728950400000,
                Timestamp.valueOf("2024-10-15 15:21:10.486")
            ),
            Todo(
                UUID.randomUUID(),
                "ygfr",
                1729382400000,
                Timestamp.valueOf("2024-10-15 15:21:16.449")
            ),
            Todo(
                UUID.randomUUID(),
                "fdfg",
                null,
                Timestamp.valueOf("2024-10-15 15:21:19.18")
            ),
            Todo(
                UUID.randomUUID(),
                "ppp",
                null,
                Timestamp.valueOf("2024-10-15 15:21:21.752")
            )
        )
    }
}