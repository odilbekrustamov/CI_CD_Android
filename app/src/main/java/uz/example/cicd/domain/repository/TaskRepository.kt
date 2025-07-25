package uz.example.cicd.domain.repository

import uz.example.cicd.domain.model.ToDoTask

interface TaskRepository {
    suspend fun getTasks(): List<ToDoTask>

    suspend fun addTask(task: ToDoTask)
}
