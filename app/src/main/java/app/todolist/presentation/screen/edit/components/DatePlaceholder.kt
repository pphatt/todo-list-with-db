package app.todolist.presentation.screen.edit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.EventBusy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DatePlaceholder(
    date: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFfcfcfc)
        ),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp, end = 20.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (date == "No date was set") {
                Icon(Icons.Rounded.EventBusy, null)
            } else {
                Icon(Icons.Rounded.CalendarMonth, null)
            }

            Spacer(Modifier.width(12.dp))

            Text(
                modifier = Modifier
                    .wrapContentHeight(), text = date
            )
        }
    }
}
