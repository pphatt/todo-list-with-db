package app.todolist.infrastructure.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.request.EditTodoDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE status = 0")
    fun getAllUnfinishedTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE status = 1")
    fun getAllFinishedTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo where deletedAt is not null")
    fun getAllTrashTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Long): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo): Long

    @Query("UPDATE todo SET status = :status WHERE id = :id")
    suspend fun checkTodo(id: Long, status: Boolean)

    @Query("UPDATE todo SET content = :content, dueDate= :dueDate WHERE id = :id")
    suspend fun updateTodo(id: Long, content: String, dueDate: Long?)

    @Query("UPDATE todo SET deletedAt = :deletedAt WHERE id = :id")
    suspend fun softDeleteTodo(id: Long, deletedAt: Long?)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodo(id: Long)

    @Query("UPDATE todo SET deletedAt = null where id = :id")
    suspend fun restoreTodo(id: Long)
}
