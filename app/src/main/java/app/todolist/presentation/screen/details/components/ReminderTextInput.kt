package app.todolist.presentation.screen.details.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.todolist.presentation.screen.details.viewmodel.ViewAction

@Composable
fun ReminderTextInput(
    content: String,
    onChange: (String) -> Unit,
    focusRequester: FocusRequester,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .heightIn(min = 200.dp)
            .wrapContentHeight(align = Alignment.CenterVertically, unbounded = true)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            .background(Color(0xFFfcfcfc)),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(120.dp))

        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Color(0xFF83cae8)
            ),
            modifier = Modifier
                .wrapContentHeight()
                .focusRequester(focusRequester),
            textStyle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W700),
            placeholder = {
                Text(
                    text = "Reminder goes here",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFFaeaeae),
                        fontWeight = FontWeight.W500
                    )
                )
            },
            value = content,
            onValueChange = {
                if (it.length > 256) {
                    Toast.makeText(
                        context,
                        "Maximum reminder words is 256",
                        Toast.LENGTH_LONG
                    ).show()

                    return@OutlinedTextField
                }

                onChange(it)
            }
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}
