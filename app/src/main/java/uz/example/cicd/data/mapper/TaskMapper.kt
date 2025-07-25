package uz.example.cicd.data.mapper

import uz.example.cicd.data.model.TaskEntity
import uz.example.cicd.domain.model.ToDoTask

fun TaskEntity.toDomain(): ToDoTask {
    return ToDoTask(id, title, description, isDone)
}

fun ToDoTask.toEntity(): TaskEntity {
    return TaskEntity(id, title, description, isDone == true)
}
