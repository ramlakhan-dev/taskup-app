package com.rl.taskup.domain.usecase

import com.rl.taskup.domain.scheduler.NotificationScheduler
import javax.inject.Inject

class ScheduleNotificationUseCase @Inject constructor(
    private val scheduler: NotificationScheduler
) {
    operator fun invoke(title: String, timeInMillis: Long) {
        scheduler.schedule(title, timeInMillis)
    }
}