package uz.example.cicd.domain.usecase

import uz.example.cicd.domain.model.ToDoTask
import uz.example.cicd.domain.repository.TaskRepository

class GetTasksUseCase(private val repository: TaskRepository) {
    suspend operator fun invoke(): List<ToDoTask> {
        return repository.getTasks()
    }
}

