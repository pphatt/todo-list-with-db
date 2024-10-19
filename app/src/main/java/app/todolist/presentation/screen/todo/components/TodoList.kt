package app.todolist.presentation.screen.todo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.todolist.domain.todo.entity.Todo
import app.todolist.presentation.request.CheckTodoDto

@Composable
fun TodoList(
    title: String,
    todos: List<Todo>,
    temporalTodos: List<Todo>?,
    onTodoClick: (Todo) -> Unit,
    onCheckTodo: (body: CheckTodoDto) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp),
            text = title,
            color = Color(0xFF9a9ea1)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            todos.forEach { todo ->
                TodoCard(
                    todo = todo,
                    isNew = temporalTodos?.find { r -> r.id == todo.id } != null,
                    onTodoClick = onTodoClick,
                    onCheckTodo = onCheckTodo
                )
            }
        }
    }
}
