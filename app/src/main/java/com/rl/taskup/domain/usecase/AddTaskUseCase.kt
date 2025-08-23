package com.rl.taskup.domain.usecase

import com.rl.taskup.domain.model.Task
import com.rl.taskup.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        taskRepository.insert(task)
    }
}