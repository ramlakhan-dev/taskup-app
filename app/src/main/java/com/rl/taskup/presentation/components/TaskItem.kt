package com.rl.taskup.presentation.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rl.taskup.domain.model.Task
import java.text.DateFormat

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task
) {
    var checked by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            CheckBox(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
                checked = checked
            ) {
                checked = it
            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyLarge
                )

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    thickness = .4.dp,
                    modifier = Modifier.fillMaxWidth(.8f).padding(vertical = 4.dp)
                )

                Text(
                    text = "Created at ${DateFormat.getDateTimeInstance().format(task.timeStamp)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}