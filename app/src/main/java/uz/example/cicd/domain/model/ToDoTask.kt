package uz.example.cicd.domain.model

data class ToDoTask(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean? = false
)

