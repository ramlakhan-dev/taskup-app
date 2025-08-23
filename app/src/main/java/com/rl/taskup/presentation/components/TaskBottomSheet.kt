package com.rl.taskup.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rl.taskup.R
import com.rl.taskup.domain.model.Task

@Composable
fun TaskBottomSheet(
    modifier: Modifier = Modifier,
    taskToEdit: Task? = null,
    onSave: (Task) -> Unit
) {
    var title by remember { mutableStateOf(taskToEdit?.title ?: "") }
    var description by remember { mutableStateOf(taskToEdit?.description ?: "") }

    Column(
        modifier = modifier
    ) {

        Text(
            text = if (taskToEdit == null) stringResource(id = R.string.create_new_task) else stringResource(id = R.string.edit_task),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text(
                    text = stringResource(id = R.string.title),
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        )

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text(
                    text = stringResource(id = R.string.description)
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val task = taskToEdit?.copy(title = title, description = description)
                        ?: Task(title = title, description = description)
                    onSave(task)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text(
                text = if (taskToEdit == null) stringResource(id = R.string.create) else stringResource(id = R.string.save)
            )
        }
    }
}