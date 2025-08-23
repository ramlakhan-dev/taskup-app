package com.rl.taskup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.taskup.domain.model.Task
import com.rl.taskup.domain.usecase.AddTaskUseCase
import com.rl.taskup.domain.usecase.GetAllTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getAllTaskUseCase: GetAllTaskUseCase
): ViewModel() {

    val allTasks: Flow<List<Task>> = getAllTaskUseCase()
    fun addTask(task: Task) {
        viewModelScope.launch {
            addTaskUseCase(task)
        }
    }
}