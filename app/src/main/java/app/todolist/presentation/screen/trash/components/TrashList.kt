package app.todolist.presentation.screen.trash.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.todolist.domain.reminder.entity.Reminder

@Composable
fun TrashList(
    date: String,
    reminders: List<Reminder>,
    onReminderClick: (Reminder) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp),
            text = date,
            color = Color(0xFF9a9ea1)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            reminders.forEach { reminder ->
                TrashCard(
                    reminder = reminder,
                    onReminderClick = {}
                )
            }
        }
    }
}
