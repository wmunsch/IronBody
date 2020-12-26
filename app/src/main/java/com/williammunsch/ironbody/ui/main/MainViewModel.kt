package com.williammunsch.ironbody.ui.main

import android.view.View
import androidx.lifecycle.*
import com.williammunsch.ironbody.room.WorkoutDao

/**
 * The view model for the entire app.
 */
class MainViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {
    val userName: String = "William"
    val benchMax5: LiveData<String> = workoutRepository.benchMax5.asLiveData()
    private val _startNewVisibility = MutableLiveData<Int>()
    val codename: LiveData<Int>
        get() = _startNewVisibility

    init {
        _startNewVisibility.value = View.VISIBLE

        //val result = workoutRepository.userName
        //userName = Transformations.map(result) { result -> result.value }

    }

    fun startNewWorkout(){
        _startNewVisibility.value = View.GONE
    }

    fun startNewCardio(){
        _startNewVisibility.value = View.GONE
    }


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
