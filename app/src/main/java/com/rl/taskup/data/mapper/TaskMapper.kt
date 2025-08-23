package com.rl.taskup.data.mapper

import com.rl.taskup.data.local.entity.TaskEntity
import com.rl.taskup.domain.model.Task

fun TaskEntity.toDomain(): Task {
    return Task(id, title, description, isCompleted, timeStamp, notifyAt)
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(id, title, description, isCompleted, timeStamp, notifyAt)
}