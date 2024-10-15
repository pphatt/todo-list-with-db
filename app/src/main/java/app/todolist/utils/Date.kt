package app.todolist.utils

import androidx.compose.material3.SelectableDates
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

object PresentOrFutureSelectableDates : SelectableDates {
    private val calender = android.icu.util.Calendar.getInstance()

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        // Only allow present or future dates (dates greater than or equal to the current time)
        val todayStartMillis = android.icu.util.Calendar.getInstance().apply {
            set(android.icu.util.Calendar.HOUR_OF_DAY, 0)
            set(android.icu.util.Calendar.MINUTE, 0)
            set(android.icu.util.Calendar.SECOND, 0)
            set(android.icu.util.Calendar.MILLISECOND, 0)
        }.timeInMillis

        return utcTimeMillis >= todayStartMillis
    }

    override fun isSelectableYear(year: Int): Boolean {
        // Only allow years that are the current year or in the future
        return year >= calender.get(android.icu.util.Calendar.YEAR)
    }
}

fun isSameDay(dueDate: Long, currentTime: Long): Boolean {
    val reminderCalendar = Calendar.getInstance().apply {
        timeInMillis = dueDate
    }
    val currentCalendar = Calendar.getInstance().apply {
        timeInMillis = currentTime
    }
    return reminderCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
            reminderCalendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)
}
