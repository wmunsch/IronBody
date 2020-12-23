package com.williammunsch.ironbody.ui.main

import androidx.lifecycle.*
import com.williammunsch.ironbody.room.WorkoutDao


class MainViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {
    val userName: String = "William"
    val benchMax5: LiveData<String> = workoutRepository.benchMax5.asLiveData()


}

class MainViewModelFactory(private val repository: WorkoutRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
