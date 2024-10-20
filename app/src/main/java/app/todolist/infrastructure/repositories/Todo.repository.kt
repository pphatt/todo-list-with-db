package app.todolist.infrastructure.repositories

import app.todolist.domain.todo.entity.Todo
import app.todolist.domain.todo.repository.TodoRepository
import app.todolist.infrastructure.database.TodoDao
import app.todolist.presentation.request.CompleteTodoDto
import app.todolist.presentation.request.CreateTodoDto
import app.todolist.presentation.request.DeleteTodoDto
import app.todolist.presentation.request.EditTodoDto
import app.todolist.presentation.request.RestoreCompleteTodoDto
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

    override suspend fun getCountAllUnfinishedTodo(): Flow<Int> {
        return dao.getCountAllUnfinishedTodo()
    }

    override suspend fun getAllFinishedTodo(): Flow<List<Todo>> {
        return dao.getAllFinishedTodo()
    }

    override suspend fun getCountAllFinishedTodo(): Flow<Int> {
        return dao.getCountAllFinishedTodo()
    }

    override suspend fun getAllDeletedTodo(): Flow<List<Todo>> {
        return dao.getAllDeletedTodo()
    }

    override suspend fun getCountAllDeletedTodo(): Flow<Int> {
        return dao.getCountAllDeletedTodo()
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

    override suspend fun completeTodo(body: CompleteTodoDto) {
        dao.completeTodo(id = body.id, completedAt = body.completedAt)
    }

    override suspend fun restoreCompleteTodo(body: RestoreCompleteTodoDto) {
        dao.restoreCompleteTodo(id = body.id)
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
