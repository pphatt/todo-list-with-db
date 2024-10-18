package app.todolist.infrastructure.repositories

import app.todolist.domain.todo.entity.Todo
import app.todolist.domain.todo.repository.TodoRepository
import app.todolist.infrastructure.database.TodoDao
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.EditTodoDto
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getAllTodo(): Flow<List<Todo>> {
        return dao.getAllTodo()
    }

    override suspend fun getTodoById(id: Long): Todo? {
        return dao.getTodoById(id)
    }

    override suspend fun createTodo(body: CreateTodoDto) : Todo {
        val newTodo = Todo(
            content = body.content,
            dueDate = body.dueDate,
        )

        val id = dao.insertTodo(newTodo)

        return newTodo.copy(id = id)
    }

    override suspend fun editTodo(body: EditTodoDto) {
        dao.updateTodo(id = body.id, content = body.content, dueDate = body.dueDate)
    }

    override suspend fun moveTodoToTrash(todo: Todo) {
//        val updatedTodoList = todoList.value.toMutableList().apply {
//            val index = indexOfFirst { it.id == todo.id }
//
//            if (index != -1) {
//                val updatedTodo =
//                    todo.copy(deletedAt = Timestamp(System.currentTimeMillis()))
//                set(index, updatedTodo)
//            }
//        }
//
//        todoList.value = updatedTodoList
    }

    override suspend fun deleteTodo(id: String) {
//        val todoToDelete = getTodoById(UUID.fromString(id))
//
//        todoToDelete?.let { todo ->
//            todoList.value = todoList.value.toMutableList().apply {
//                remove(todo)
//            }
//        }
    }

    override suspend fun restoreTodo(id: String) {
//        val updatedTodoList = todoList.value.toMutableList().apply {
//            val index = indexOfFirst { it.id == UUID.fromString(id) }
//
//            if (index != -1) {
//                val todo = get(index)
//
//                val updatedTodo = todo.copy(deletedAt = null)
//
//                set(index, updatedTodo)
//            }
//        }
//
//        todoList.value = updatedTodoList
    }
}
