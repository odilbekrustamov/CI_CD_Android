package uz.example.cicd.presentation.screen.task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.example.cicd.domain.model.ToDoTask
import uz.example.cicd.domain.usecase.AddTaskUseCase
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
    @Inject
    constructor(
        private val addTaskUseCase: AddTaskUseCase,
    ) : ViewModel() {
        var title by mutableStateOf("")
            private set

        var description by mutableStateOf("")
            private set

        var isSaving by mutableStateOf(false)
            private set

        fun onTitleChange(newTitle: String) {
            title = newTitle
        }

        fun onDescriptionChange(newDesc: String) {
            description = newDesc
        }

        fun saveTask(onSaved: () -> Unit) {
            if (title.isBlank()) return

            viewModelScope.launch {
                isSaving = true
                addTaskUseCase(ToDoTask(title = title, description = description))
                isSaving = false
                onSaved()
            }
        }
    }
