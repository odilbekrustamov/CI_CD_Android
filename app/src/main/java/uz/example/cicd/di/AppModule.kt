package uz.example.cicd.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.example.cicd.data.local.TaskDao
import uz.example.cicd.data.local.TaskDatabase
import android.content.Context

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): TaskDatabase =
        Room
            .databaseBuilder(
                context,
                TaskDatabase::class.java,
                "task_db",
            ).build()

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao = db.taskDao()
}
