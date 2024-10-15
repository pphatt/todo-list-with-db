package app.todolist.presentation.screen.reminder.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.todolist.ui.theme.LocalColorScheme
import kotlinx.coroutines.Job
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.todolist.presentation.screen.details.components.convertMillisToDate
import app.todolist.ui.main.LocalRemindersList
import app.todolist.utils.isSameDay
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReminderScreen(
    navController: NavController,
    openDrawer: () -> Job
) {
    val reminders = LocalRemindersList.current

    val context = LocalContext.current

    Scaffold(
        containerColor = LocalColorScheme.current.primaryBackgroundColor,
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        floatingActionButton = { AddReminderButton(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier.padding(
                    bottom = 10.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, top = 25.dp, end = 5.dp, bottom = 15.dp)
                        .height(48.dp)
                        .background(Color.Transparent),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(5.dp))

                    IconButton(
                        onClick = { openDrawer() }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            tint = MaterialTheme.colorScheme.outline,
                            contentDescription = "",
                        )
                    }

                    Text(
                        text = "All Reminders",
                        modifier = Modifier.weight(0.8f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                val currentTimeMillis = System.currentTimeMillis()
                val currentCalendar = Calendar.getInstance()

                val remindersFilteredByPastDate = reminders.filter { reminder ->
                    reminder.dueDate != null && reminder.dueDate < currentTimeMillis && isSameDay(
                        reminder.dueDate,
                        currentCalendar.timeInMillis
                    ).not()
                }

                val remindersFilteredByCurrentDate = reminders.filter { reminder ->
                    reminder.dueDate != null && isSameDay(
                        reminder.dueDate,
                        currentCalendar.timeInMillis
                    )
                }

                val remindersFilteredByFutureDate = reminders.filter { reminder ->
                    reminder.dueDate != null && reminder.dueDate > currentTimeMillis && isSameDay(
                        reminder.dueDate,
                        currentCalendar.timeInMillis
                    ).not()
                }

                val remindersFilteredByNoDate =
                    reminders.filter { reminder -> reminder.dueDate == null }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (remindersFilteredByPastDate.isNotEmpty()) {
                        item {
                            ReminderItemLayout(title = "Past date") {
                                remindersFilteredByPastDate.forEach { reminder ->
                                    ReminderItem(
                                        content = reminder.content,
                                        dueDate = reminder.dueDate
                                    )
                                }
                            }
                        }
                    }

                    if (remindersFilteredByCurrentDate.isNotEmpty()) {
                        item {
                            ReminderItemLayout(title = "Current date") {
                                remindersFilteredByCurrentDate.forEach { reminder ->
                                    ReminderItem(
                                        content = reminder.content,
                                        dueDate = reminder.dueDate
                                    )
                                }
                            }
                        }
                    }

                    if (remindersFilteredByFutureDate.isNotEmpty()) {
                        item {
                            ReminderItemLayout(title = "Future date") {
                                remindersFilteredByFutureDate.forEach { reminder ->
                                    ReminderItem(
                                        content = reminder.content,
                                        dueDate = reminder.dueDate
                                    )
                                }
                            }
                        }
                    }

                    if (remindersFilteredByNoDate.isNotEmpty()) {
                        item {
                            ReminderItemLayout(title = "No date") {
                                remindersFilteredByNoDate.forEach { reminder ->
                                    ReminderItem(
                                        content = reminder.content,
                                        dueDate = reminder.dueDate
                                    )
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                if (reminders.isNotEmpty()) {
                    val currentTime = System.currentTimeMillis()

                    val reminderTimeMillis = reminders.last().timestamp.time

                    if (reminderTimeMillis >= currentTime - 100) {
                        Toast.makeText(
                            context,
                            "Save reminders successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

@Composable
fun ReminderItemLayout(title: String, content: @Composable () -> Unit) {
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
            content.invoke()
        }
    }
}

@Composable
fun ReminderItem(
    content: String,
    dueDate: Long?
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFfcfcfc)
        ),
        shape = RoundedCornerShape(15.dp)
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
                Text(
                    text = content,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )

                if (dueDate != null) {
                    Text(
                        text = convertMillisToDate(dueDate),
                        color = Color(0xFFed842f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
    }
}
