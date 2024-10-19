package app.todolist.infrastructure.repositories

import app.todolist.domain.todo.entity.Todo
import app.todolist.domain.todo.repository.TodoRepository
import app.todolist.infrastructure.database.TodoDao
import app.todolist.presentation.request.CheckTodoDto
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.EditTodoDto
import app.todolist.presentation.request.RestoreTodoDto
import app.todolist.presentation.request.SoftDeleteTodoDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun getAllTodo(): Flow<List<Todo>> {
        return dao.getAllTodo()
    }

    override suspend fun getAllUnfinishedTodo(): Flow<List<Todo>> {
        return dao.getAllUnfinishedTodo()
    }

    override suspend fun getAllFinishedTodo(): Flow<List<Todo>> {
        return dao.getAllFinishedTodo()
    }

    override suspend fun getAllTrashTodo(): Flow<List<Todo>> {
        return dao.getAllTrashTodo()
    }

    override suspend fun getTodoById(id: Long): Todo? {
        return dao.getTodoById(id)
    }

    override suspend fun createTodo(body: CreateTodoDto): Todo {
        val newTodo = Todo(
            content = body.content,
            dueDate = body.dueDate,
        )

        val id = dao.insertTodo(newTodo)

        return newTodo.copy(id = id)
    }

    override suspend fun checkTodo(body: CheckTodoDto) {
        dao.completeTodo(id = body.id, status = !body.status, completedAt = body.completedAt)
    }

    override suspend fun editTodo(body: EditTodoDto) {
        dao.updateTodo(id = body.id, content = body.content, dueDate = body.dueDate)
    }

    override suspend fun softDeleteTodo(body: SoftDeleteTodoDto) {
        dao.softDeleteTodo(id = body.id, deletedAt = body.deletedAt)
    }

    override suspend fun deleteTodo(body: DeleteTodoDto) {
        dao.deleteTodo(id = body.id)
    }

    override suspend fun restoreTodo(body: RestoreTodoDto) {
        dao.restoreTodo(id = body.id)
    }
}
