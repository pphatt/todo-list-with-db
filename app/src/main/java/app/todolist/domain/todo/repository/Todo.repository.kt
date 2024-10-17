package app.todolist.domain.todo.repository

import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.EditTodoDto
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface TodoRepository {
    suspend fun getAllTodo(): Flow<List<Todo>>
    suspend fun getTodoById(id: UUID): Todo?
    suspend fun createTodo(body: CreateTodoDto)
    suspend fun editTodo(body: EditTodoDto)
    suspend fun moveTodoToTrash(todo: Todo)
    suspend fun deleteTodo(id: String)
    suspend fun restoreTodo(id: String)
}
