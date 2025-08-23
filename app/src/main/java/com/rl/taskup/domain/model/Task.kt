package com.rl.taskup.domain.model

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val timeStamp: Long = System.currentTimeMillis(),
    val notifyAt: Long? = null
)
