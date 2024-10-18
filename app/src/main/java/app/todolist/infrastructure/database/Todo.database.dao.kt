package app.todolist.infrastructure.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.todolist.domain.todo.entity.Todo
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Singleton

@Singleton
@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Long): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo) : Long

    @Delete
    suspend fun deleteTodo(todo: Todo)
}
