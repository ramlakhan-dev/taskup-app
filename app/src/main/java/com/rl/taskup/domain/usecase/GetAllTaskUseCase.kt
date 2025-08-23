package com.rl.taskup.domain.usecase

import com.rl.taskup.domain.model.Task
import com.rl.taskup.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){
    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getAllTasks()
    }
}