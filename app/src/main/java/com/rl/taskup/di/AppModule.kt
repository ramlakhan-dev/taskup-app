package com.rl.taskup.di

import android.content.Context
import com.rl.taskup.data.local.dao.TaskDao
import com.rl.taskup.data.repository.TaskRepositoryImpl
import com.rl.taskup.data.scheduler.NotificationSchedulerImpl
import com.rl.taskup.domain.repository.TaskRepository
import com.rl.taskup.domain.scheduler.NotificationScheduler
import com.rl.taskup.domain.usecase.AddTaskUseCase
import com.rl.taskup.domain.usecase.DeleteTaskUseCase
import com.rl.taskup.domain.usecase.GetAllTaskUseCase
import com.rl.taskup.domain.usecase.UpdateTaskUseCase
import com.rl.taskup.utils.AlarmPermissionHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }

    @Provides
    @Singleton
    fun provideAddTaskUseCase(repository: TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllTaskUseCase(repository: TaskRepository): GetAllTaskUseCase {
        return GetAllTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTaskUseCase(repository: TaskRepository): UpdateTaskUseCase {
        return UpdateTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideNotificationScheduler(@ApplicationContext context: Context, alarmPermissionHelper: AlarmPermissionHelper): NotificationScheduler {
        return NotificationSchedulerImpl(context, alarmPermissionHelper)
    }

}