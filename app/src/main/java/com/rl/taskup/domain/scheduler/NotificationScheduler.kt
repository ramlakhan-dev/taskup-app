package com.rl.taskup.domain.scheduler

interface NotificationScheduler {
    fun schedule(title: String, timeInMillis: Long)
}