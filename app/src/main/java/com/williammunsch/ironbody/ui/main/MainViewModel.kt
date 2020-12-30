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

    private val _workoutButtonLayoutVisibility = MutableLiveData<Int>()
    val workoutButtonLayoutVisibility: LiveData<Int>
        get() = _workoutButtonLayoutVisibility
    private val _newLiftButtonVisibility = MutableLiveData<Int>()
    val liftButtonVisibility: LiveData<Int>
        get() = _newLiftButtonVisibility

    init {
        _startNewVisibility.value = View.VISIBLE
        _workoutButtonLayoutVisibility.value = View.INVISIBLE
        _newLiftButtonVisibility.value = View.INVISIBLE
        //val result = workoutRepository.userName
        //userName = Transformations.map(result) { result -> result.value }

    }

    fun startNewWorkout(){
        _startNewVisibility.value = View.GONE
        _newLiftButtonVisibility.value = View.VISIBLE
    }

    fun startNewCardio(){
        _startNewVisibility.value = View.GONE
        _newLiftButtonVisibility.value = View.VISIBLE
    }

    fun addLift(){
        _workoutButtonLayoutVisibility.value = View.VISIBLE
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
