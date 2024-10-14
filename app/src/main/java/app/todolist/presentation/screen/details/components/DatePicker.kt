package app.todolist.presentation.screen.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.LibraryAddCheck
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import app.todolist.ui.theme.LocalColorScheme
import app.todolist.utils.getCurrentDateTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DatePickerDocked(
    date: DatePickerState,
    showDatePicker: Boolean,
    onToggleShowDatePicker: () -> Unit
) {
    val selectedDate = date.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Column(
        modifier = Modifier
            .wrapContentHeight(
                align = Alignment.CenterVertically
            )
            .fillMaxWidth()
            .background(Color(0xFFfcfcfc)),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight(
                    align = Alignment.CenterVertically
                )
                .background(Color.Transparent)
                .fillMaxWidth()
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = onToggleShowDatePicker
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Date Time",
                    fontWeight = if (showDatePicker) FontWeight.Bold else FontWeight.Normal
                )

                Switch(
                    colors = SwitchDefaults.colors(
                        uncheckedBorderColor = Color.Transparent,

                        uncheckedTrackColor = LocalColorScheme.current.unCheckTrackColor,
                        checkedTrackColor = LocalColorScheme.current.trackColor,

                        uncheckedThumbColor = LocalColorScheme.current.thumbColor,
                        checkedThumbColor = LocalColorScheme.current.thumbColor,
                    ),
                    checked = showDatePicker,
                    onCheckedChange = { onToggleShowDatePicker() },
                )
            }
        }

        AnimatedVisibility(
            visible = showDatePicker,
            enter = expandVertically(animationSpec = tween(durationMillis = 200)) + fadeIn(
                animationSpec = tween(
                    durationMillis = 1000
                )
            ),
            exit = shrinkVertically() + fadeOut(animationSpec = tween(durationMillis = 200))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Rounded.CalendarMonth, null)

                    Spacer(Modifier.width(12.dp))

                    OutlinedTextField(
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                        ),
                        value = selectedDate,
                        onValueChange = { },
                        readOnly = true,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                }

                DatePicker(
                    modifier = Modifier.fillMaxWidth(),
                    state = date,
                    title = null,
                    headline = null,
                    colors = DatePickerDefaults.colors(
                        containerColor = Color(0xFFfcfcfc),
                        selectedDayContainerColor = LocalColorScheme.current.trackColor
                    ),
                    showModeToggle = false,
                )
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return formatter.format(Date(millis))
}
