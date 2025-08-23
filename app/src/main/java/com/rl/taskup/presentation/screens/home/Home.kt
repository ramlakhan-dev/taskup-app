package com.rl.taskup.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rl.taskup.R
import com.rl.taskup.domain.model.Task
import com.rl.taskup.presentation.components.TaskItem
import com.rl.taskup.presentation.viewmodel.TaskViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    onTaskItemClick: (Task) -> Unit,
    onTaskItemLongPressed: (Task) -> Unit
) {

    val taskList = taskViewModel.allTasks.collectAsState(initial = emptyList()).value
    if (taskList.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_tasks)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(taskList) { task ->
                TaskItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    task = task,
                    onTaskItemClick = {
                        onTaskItemClick(it)
                    },
                    onTaskItemLongPressed = {
                        onTaskItemLongPressed(it)
                    },
                    onCheckedChange = { isChecked ->
                        val completedTask = if (isChecked) task.copy(isCompleted = true) else task.copy(isCompleted = false)
                        taskViewModel.updateTask(completedTask)
                    }
                )
            }
        }
    }
}