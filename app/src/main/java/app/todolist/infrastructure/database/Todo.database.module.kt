package app.todolist.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.todolist.domain.todo.entity.Todo

@Database(
    entities = [Todo::class],
    version = 1
)

abstract class TodoDatabaseModule : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object{
        const val DB_NAME = "todo_list_db"
    }
}
