package com.rl.taskup.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rl.taskup.R
import com.rl.taskup.domain.model.Task
import com.rl.taskup.presentation.components.DeleteTaskDialog
import com.rl.taskup.presentation.components.TaskBottomSheet
import com.rl.taskup.presentation.screens.home.Home
import com.rl.taskup.presentation.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskUpApp(taskViewModel: TaskViewModel = hiltViewModel()) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }

    if (showDeleteDialog && taskToDelete != null) {
        DeleteTaskDialog(
            modifier = Modifier.padding(16.dp),
            onConfirm = {
                taskViewModel.deleteTask(taskToDelete!!)
                showDeleteDialog = false
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }


    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showSheet = false
            },
            sheetState = sheetState
        ) {
            TaskBottomSheet(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                taskToEdit = taskToEdit
            ) { task ->
                if (task.id == 0) {
                    taskViewModel.addTask(task)
                } else {
                    taskViewModel.updateTask(task)
                }

                showSheet = false
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.tasks))
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    taskToEdit = null
                    showSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }
        }
    ) { innerPadding ->
        Home(
            modifier = Modifier.padding(innerPadding),
            taskViewModel = taskViewModel,
            onTaskItemClick = { task ->
                taskToEdit = task
                showSheet = true
            },
            onTaskItemLongPressed = { task ->
                taskToDelete = task
                showDeleteDialog = true
            }
        )
    }
}