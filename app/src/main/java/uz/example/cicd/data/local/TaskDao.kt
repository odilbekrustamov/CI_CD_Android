package uz.example.cicd.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.example.cicd.data.model.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskEntity)
}
