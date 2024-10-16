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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import app.todolist.ui.theme.LocalColorScheme
import kotlinx.coroutines.Job
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.todolist.domain.reminder.entity.Reminder
import app.todolist.presentation.screen.reminder.viewmodel.ReminderScreenViewModel
import app.todolist.utils.isSameDay
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReminderScreen(
    viewModel: ReminderScreenViewModel = hiltViewModel(),
    openDrawer: () -> Job,
    navigateToDetails: () -> Unit,
    temporalReminders: SnapshotStateList<Reminder>?,
    onReminderClick: (Reminder) -> Unit
) {
    val state = viewModel.uiState.collectAsState().value

    val context = LocalContext.current

    Scaffold(
        containerColor = LocalColorScheme.current.primaryBackgroundColor,
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        floatingActionButton = {
            AddReminderButton(navigateToDetails = navigateToDetails)
        }
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

                val nonDeletedList = state.list.filter { reminder -> !reminder.isDeleted }

                val remindersFilteredByPastDate = nonDeletedList.filter { reminder ->
                    reminder.dueDate != null && reminder.dueDate < currentTimeMillis && isSameDay(
                        reminder.dueDate,
                        currentCalendar.timeInMillis
                    ).not()
                }

                val remindersFilteredByCurrentDate = nonDeletedList.filter { reminder ->
                    reminder.dueDate != null && isSameDay(
                        reminder.dueDate,
                        currentCalendar.timeInMillis
                    )
                }

                val remindersFilteredByFutureDate = nonDeletedList.filter { reminder ->
                    reminder.dueDate != null && reminder.dueDate > currentTimeMillis && isSameDay(
                        reminder.dueDate,
                        currentCalendar.timeInMillis
                    ).not()
                }

                val remindersFilteredByNoDate =
                    nonDeletedList.filter { reminder -> reminder.dueDate == null }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp)),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (remindersFilteredByPastDate.isNotEmpty()) {
                        item {
                            ReminderList(
                                title = "Past date",
                                reminders = remindersFilteredByPastDate,
                                temporalReminders = temporalReminders,
                                onReminderClick = onReminderClick
                            )
                        }
                    }

                    if (remindersFilteredByCurrentDate.isNotEmpty()) {
                        item {
                            ReminderList(
                                title = "Current date",
                                reminders = remindersFilteredByCurrentDate,
                                temporalReminders = temporalReminders,
                                onReminderClick = onReminderClick
                            )
                        }
                    }

                    if (remindersFilteredByFutureDate.isNotEmpty()) {
                        item {
                            ReminderList(
                                title = "Future date",
                                reminders = remindersFilteredByFutureDate,
                                temporalReminders = temporalReminders,
                                onReminderClick = onReminderClick
                            )
                        }
                    }

                    if (remindersFilteredByNoDate.isNotEmpty()) {
                        item {
                            ReminderList(
                                title = "No date",
                                reminders = remindersFilteredByNoDate,
                                temporalReminders = temporalReminders,
                                onReminderClick = onReminderClick
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                if (state.list.isNotEmpty()) {
                    val currentTime = System.currentTimeMillis()

                    val reminderTimeMillis = state.list.last().timestamp.time

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
