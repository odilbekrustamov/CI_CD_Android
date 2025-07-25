package uz.example.cicd.presentation.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.example.cicd.domain.model.ToDoTask
import uz.example.cicd.domain.usecase.GetTasksUseCase
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {


    private val _tasks = mutableStateOf<List<ToDoTask>>(emptyList())
    val tasks: State<List<ToDoTask>> = _tasks

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            _tasks.value = getTasksUseCase()
            _isLoading.value = false
        }
    }
}
