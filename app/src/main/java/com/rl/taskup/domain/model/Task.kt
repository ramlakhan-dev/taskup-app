package com.rl.taskup.domain.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val timeStamp: Long,
    val notifyAt: Long?
)
