package app.todolist.presentation.screen.edit.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    onEditTodo: () -> Unit,
    onDeleteTodo: () -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent
    ) {
        NavigationBarItem(
            label = { Text("Edit", fontSize = 15.sp) },
            icon = {
                Icon(Icons.Rounded.Edit, null)
            },
            selected = false,
            onClick = onEditTodo
        )
        NavigationBarItem(
            label = { Text("Delete", fontSize = 15.sp) },
            icon = {
                Icon(Icons.Rounded.Delete, null)
            },
            selected = false,
            onClick = onDeleteTodo
        )
    }
}
