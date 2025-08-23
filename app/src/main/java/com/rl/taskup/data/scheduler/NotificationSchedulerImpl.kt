package com.rl.taskup.data.scheduler

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import com.rl.taskup.domain.scheduler.NotificationScheduler
import com.rl.taskup.utils.AlarmPermissionHelper
import javax.inject.Inject

class NotificationSchedulerImpl @Inject constructor(
    private val context: Context,
    private val alarmPermissionHelper: AlarmPermissionHelper
) : NotificationScheduler{
    @SuppressLint("ScheduleExactAlarm")
    @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    override fun schedule(title: String, timeInMillis: Long) {
        if (!alarmPermissionHelper.canScheduleExactAlarms()) {
            alarmPermissionHelper.requestExactAlarmPermission()
            return
        }
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("title", title)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            timeInMillis.toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )
    }
}