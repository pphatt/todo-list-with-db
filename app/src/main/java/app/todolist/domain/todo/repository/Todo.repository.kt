package app.todolist.domain.todo.repository

import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.request.CheckTodoDto
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.EditTodoDto
import app.todolist.presentation.request.RestoreTodoDto
import app.todolist.presentation.request.SoftDeleteTodoDto
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getAllTodo(): Flow<List<Todo>>
    suspend fun getAllUnfinishedTodo(): Flow<List<Todo>>
    suspend fun getAllFinishedTodo(): Flow<List<Todo>>
    suspend fun getAllTrashTodo(): Flow<List<Todo>>
    suspend fun getTodoById(id: Long): Todo?
    suspend fun createTodo(body: CreateTodoDto) : Todo
    suspend fun checkTodo(body: CheckTodoDto)
    suspend fun editTodo(body: EditTodoDto)
    suspend fun softDeleteTodo(body: SoftDeleteTodoDto)
    suspend fun deleteTodo(body: DeleteTodoDto)
    suspend fun restoreTodo(body: RestoreTodoDto)
}
