package com.rl.taskup.presentation.components

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rl.taskup.R
import com.rl.taskup.domain.model.Task
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskBottomSheet(
    modifier: Modifier = Modifier,
    taskToEdit: Task? = null,
    onSave: (Task) -> Unit
) {
    var title by remember { mutableStateOf(taskToEdit?.title ?: "") }
    var description by remember { mutableStateOf(taskToEdit?.description ?: "") }
    var showDateTimePicker by remember { mutableStateOf(false) }

    val dateTimeToEdit = taskToEdit?.notifyAt?.let { Instant.ofEpochMilli(it) }
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalDateTime()
    var selectedDateTime by remember { mutableStateOf(dateTimeToEdit) }

    if (showDateTimePicker) {
        DateTimePickerDialog(
            modifier = Modifier.padding(16.dp),
            onDateTimeSelected = {dateTime ->
                selectedDateTime = dateTime
                showDateTimePicker = false
            }
        )
    }

    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showDateTimePicker = true
        } else {
            Toast.makeText(context, "Notification permission denied", Toast.LENGTH_SHORT).show()
        }
    }

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

        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                            showDateTimePicker = true
                        }else {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                    } else {
                        showDateTimePicker = true
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = stringResource(id = R.string.notification)
                )
            }

            if (selectedDateTime != null) {
                Text(
                    text = selectedDateTime.toString()
                )
            }

        }

        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val millis = selectedDateTime
                        ?.atZone(ZoneId.systemDefault())
                        ?.toInstant()
                        ?.toEpochMilli()

                    val task = taskToEdit?.copy(title = title, description = description, notifyAt = millis)
                        ?: Task(title = title, description = description, notifyAt = millis)
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