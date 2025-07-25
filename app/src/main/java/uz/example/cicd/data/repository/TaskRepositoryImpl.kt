package uz.example.cicd.data.repository

import uz.example.cicd.data.local.TaskDao
import uz.example.cicd.data.mapper.toDomain
import uz.example.cicd.data.mapper.toEntity
import uz.example.cicd.domain.model.ToDoTask
import uz.example.cicd.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val dao: TaskDao,
) : TaskRepository {
    override suspend fun getTasks(): List<ToDoTask> = dao.getTasks().map { it.toDomain() }

    override suspend fun addTask(task: ToDoTask) {
        dao.addTask(task.toEntity())
    }
}
