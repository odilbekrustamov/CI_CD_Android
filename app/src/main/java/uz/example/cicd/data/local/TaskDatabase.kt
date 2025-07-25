package uz.example.cicd.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.example.cicd.data.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
