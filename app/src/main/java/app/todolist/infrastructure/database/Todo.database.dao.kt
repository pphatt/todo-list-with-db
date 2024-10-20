package app.todolist.infrastructure.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.todolist.domain.todo.entity.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE status = 0 and deletedAt is null")
    fun getAllUnfinishedTodo(): Flow<List<Todo>>

    @Query("SELECT COUNT(id) FROM todo WHERE status = 0 and deletedAt is null")
    fun getCountAllUnfinishedTodo(): Flow<Int>

    @Query("SELECT * FROM todo WHERE status = 1 and deletedAt is null")
    fun getAllFinishedTodo(): Flow<List<Todo>>

    @Query("SELECT COUNT(id) FROM todo WHERE status = 1 and deletedAt is null")
    fun getCountAllFinishedTodo(): Flow<Int>

    @Query("SELECT * FROM todo where deletedAt is not null")
    fun getAllDeletedTodo(): Flow<List<Todo>>

    @Query("SELECT COUNT(id) FROM todo where deletedAt is not null")
    fun getCountAllDeletedTodo(): Flow<Int>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Long): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo): Long

    @Query("UPDATE todo SET status = 1, completedAt = :completedAt WHERE id = :id")
    suspend fun completeTodo(id: Long, completedAt: Long)

    @Query("UPDATE todo SET status = 0, completedAt = null WHERE id = :id")
    suspend fun restoreCompleteTodo(id: Long)

    @Query("UPDATE todo SET content = :content, dueDate= :dueDate WHERE id = :id")
    suspend fun updateTodo(id: Long, content: String, dueDate: Long?)

    @Query("UPDATE todo SET deletedAt = :deletedAt WHERE id = :id")
    suspend fun softDeleteTodo(id: Long, deletedAt: Long?)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodo(id: Long)

    @Query("UPDATE todo SET deletedAt = null where id = :id")
    suspend fun restoreTodo(id: Long)
}
