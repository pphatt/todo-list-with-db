package app.todolist.presentation.screen.reminder.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.utils.convertMillisToDate
import java.util.UUID

@Composable
fun ReminderCard(
    reminder: Reminder,
    isNew: Boolean,
    onReminderClick: (Reminder) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
                println("reminder $reminder")
                onReminderClick(reminder)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFfcfcfc)
        ),
        shape = RoundedCornerShape(15.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    if (isNew) {
                        Text(
                            text = "N",
                            color = Color(0xFFed842f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Text(
                        text = reminder.content,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600
                    )
                }

                if (reminder.dueDate != null) {
                    Text(
                        text = convertMillisToDate(reminder.dueDate),
                        color = Color(0xFFed842f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
    }
}