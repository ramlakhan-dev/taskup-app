package com.rl.taskup.di

import com.rl.taskup.data.local.dao.TaskDao
import com.rl.taskup.data.repository.TaskRepositoryImpl
import com.rl.taskup.domain.repository.TaskRepository
import com.rl.taskup.domain.usecase.AddTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}