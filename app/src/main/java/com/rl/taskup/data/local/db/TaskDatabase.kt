package com.rl.taskup.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rl.taskup.data.local.dao.TaskDao
import com.rl.taskup.data.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao
}