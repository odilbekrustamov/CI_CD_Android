package uz.example.cicd.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.example.cicd.data.local.TaskDao
import uz.example.cicd.data.repository.TaskRepositoryImpl
import uz.example.cicd.domain.repository.TaskRepository
import uz.example.cicd.domain.usecase.AddTaskUseCase
import uz.example.cicd.domain.usecase.GetTasksUseCase

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {
    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(dao)
    }

    @Provides
    fun provideAddTaskUseCase(repository: TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(repository)
    }

    @Provides
    fun provideGetTasksUseCase(repository: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(repository)
    }
}
