package uz.example.cicd.domain.usecase

import uz.example.cicd.domain.model.ToDoTask
import uz.example.cicd.domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository,
) {
    suspend operator fun invoke(task: ToDoTask) {
        repository.addTask(task)
    }
}
