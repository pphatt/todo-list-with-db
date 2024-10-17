package app.todolist.infrastructure.repositories

import app.todolist.domain.todo.data.TodoInitialData
import app.todolist.domain.todo.entity.Todo
import app.todolist.domain.todo.repository.TodoRepository
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.EditTodoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.sql.Timestamp
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor() : TodoRepository {
    private val todoList = MutableStateFlow(
        mutableListOf(
            *TodoInitialData.default.toTypedArray()
        )
    )

    override suspend fun getAllTodo(): Flow<List<Todo>> {
        return todoList
    }

    override suspend fun getTodoById(id: UUID): Todo? {
        return todoList.value.find { it.id == id }
    }

    override suspend fun createTodo(body: CreateTodoDto) {
        // TODO: change this back dto when using db
        val newTodo = Todo(
            id = body.id,
            content = body.content,
            dueDate = body.dueDate,
            createdAt = body.createdAt
        )

        todoList.value.add(newTodo)
    }

    override suspend fun editTodo(body: EditTodoDto) {
        val todoIndex = todoList.value.indexOfFirst { it.id == body.id }

        if (todoIndex != -1) {
            val updatedTodo = Todo(
                id = body.id,
                content = body.content,
                dueDate = body.dueDate,
                createdAt = body.createdAt
            )

            val updatedList = todoList.value.toMutableList()
            updatedList[todoIndex] = updatedTodo

            todoList.value = updatedList
        }
    }

    override suspend fun moveTodoToTrash(todo: Todo) {
        val updatedTodoList = todoList.value.toMutableList().apply {
            val index = indexOfFirst { it.id == todo.id }

            if (index != -1) {
                val updatedTodo =
                    todo.copy(deletedAt = Timestamp(System.currentTimeMillis()))
                set(index, updatedTodo)
            }
        }

        todoList.value = updatedTodoList
    }

    override suspend fun deleteTodo(id: String) {
        val todoToDelete = getTodoById(UUID.fromString(id))

        todoToDelete?.let { todo ->
            todoList.value = todoList.value.toMutableList().apply {
                remove(todo)
            }
        }
    }

    override suspend fun restoreTodo(id: String) {
        val updatedTodoList = todoList.value.toMutableList().apply {
            val index = indexOfFirst { it.id == UUID.fromString(id) }

            if (index != -1) {
                val todo = get(index)

                val updatedTodo = todo.copy(deletedAt = null)

                set(index, updatedTodo)
            }
        }

        todoList.value = updatedTodoList
    }
}
