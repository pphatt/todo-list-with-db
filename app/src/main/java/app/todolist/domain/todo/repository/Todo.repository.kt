package app.todolist.domain.todo.repository

import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.request.CompleteTodoDto
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.EditTodoDto
import app.todolist.presentation.request.RestoreCompleteTodoDto
import app.todolist.presentation.request.RestoreTodoDto
import app.todolist.presentation.request.SoftDeleteTodoDto
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getAllTodo(): Flow<List<Todo>>
    suspend fun getAllUnfinishedTodo(): Flow<List<Todo>>
    suspend fun getCountAllUnfinishedTodo(): Flow<Int>
    suspend fun getAllFinishedTodo(): Flow<List<Todo>>
    suspend fun getCountAllFinishedTodo(): Flow<Int>
    suspend fun getAllDeletedTodo(): Flow<List<Todo>>
    suspend fun getCountAllDeletedTodo(): Flow<Int>
    suspend fun getTodoById(id: Long): Todo?
    suspend fun createTodo(body: CreateTodoDto) : Todo
    suspend fun completeTodo(body: CompleteTodoDto)
    suspend fun restoreCompleteTodo(body: RestoreCompleteTodoDto)
    suspend fun editTodo(body: EditTodoDto)
    suspend fun softDeleteTodo(body: SoftDeleteTodoDto)
    suspend fun deleteTodo(body: DeleteTodoDto)
    suspend fun restoreTodo(body: RestoreTodoDto)
}
