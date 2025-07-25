package uz.example.cicd.data.mapper

import uz.example.cicd.data.model.TaskEntity
import uz.example.cicd.domain.model.ToDoTask

fun TaskEntity.toDomain(): ToDoTask = ToDoTask(id, title, description, isDone)

fun ToDoTask.toEntity(): TaskEntity = TaskEntity(id, title, description, isDone == true)
