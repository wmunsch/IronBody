package com.williammunsch.ironbody.ui.main

import android.view.View
import androidx.lifecycle.*
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * The view model for the entire app.
 */
class MainViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {
    val userName: String = "William"
    val benchMax5: LiveData<String> = workoutRepository.benchMax5.asLiveData()
    //Other stats will go here in the future

    //Declare all of the visibility Ints for the buttons and layouts for the workout page
    private val _startNewVisibility = MutableLiveData<Int>()
    val startNewVisibility: LiveData<Int>
        get() = _startNewVisibility
    private val _workoutButtonLayoutVisibility = MutableLiveData<Int>()
    val workoutButtonLayoutVisibility: LiveData<Int>
        get() = _workoutButtonLayoutVisibility
    private val _newLiftButtonVisibility = MutableLiveData<Int>()
    val liftButtonVisibility: LiveData<Int>
        get() = _newLiftButtonVisibility
    private val _finishButtonVisibility = MutableLiveData<Int>()
    val finishButtonVisibility: LiveData<Int>
        get() = _finishButtonVisibility

    //Ints that hold the number of the spinner choice
    var lift1Type = MutableLiveData<Int>()
    var lift2Type = MutableLiveData<Int>()
    var lift3Type = MutableLiveData<Int>()
    var lift4Type = MutableLiveData<Int>()
    var lift5Type = MutableLiveData<Int>()

    //Strings that hold the weight inputted into the edit text field
    var lift1Weight = MutableLiveData<String>()
    var lift2Weight = MutableLiveData<String>()
    var lift3Weight = MutableLiveData<String>()
    var lift4Weight = MutableLiveData<String>()
    var lift5Weight = MutableLiveData<String>()

    //Strings that hold the numbers of reps inputted into the edit text field
    var lift1Reps = MutableLiveData<String>()
    var lift2Reps = MutableLiveData<String>()
    var lift3Reps = MutableLiveData<String>()
    var lift4Reps = MutableLiveData<String>()
    var lift5Reps = MutableLiveData<String>()

    //Ints that hold the visibility for each row of workout layouts
    val lift1Visibility = MutableLiveData<Int>()
    val lift2Visibility = MutableLiveData<Int>()
    val lift3Visibility = MutableLiveData<Int>()
    val lift4Visibility = MutableLiveData<Int>()
    val lift5Visibility = MutableLiveData<Int>()



    init {
        _startNewVisibility.value = View.VISIBLE
        _workoutButtonLayoutVisibility.value = View.INVISIBLE
        _newLiftButtonVisibility.value = View.INVISIBLE
        _finishButtonVisibility.value = View.INVISIBLE
        lift1Visibility.value = View.INVISIBLE
        lift2Visibility.value = View.INVISIBLE
        lift3Visibility.value = View.INVISIBLE
        lift4Visibility.value = View.INVISIBLE
        lift5Visibility.value = View.INVISIBLE
    }

    /**
     * Starts a new workout by hiding the lift button and textview
     * and making visible the new lift and finish buttons
     */
    fun startNewWorkout(){
        _startNewVisibility.value = View.GONE
        _newLiftButtonVisibility.value = View.VISIBLE
        _finishButtonVisibility.value = View.VISIBLE
    }

    /**
     * Makes each row of the workout layouts visible, one after the other
     */
    fun addLift(){
        when {
            lift1Visibility.value != View.VISIBLE -> {
                lift1Visibility.value = View.VISIBLE
            }
            lift2Visibility.value != View.VISIBLE -> {
                lift2Visibility.value = View.VISIBLE
            }
            lift3Visibility.value != View.VISIBLE -> {
                lift3Visibility.value = View.VISIBLE
            }
            lift4Visibility.value != View.VISIBLE -> {
                lift4Visibility.value = View.VISIBLE
            }
            lift5Visibility.value != View.VISIBLE -> {
                lift5Visibility.value = View.VISIBLE
                _newLiftButtonVisibility.value = View.GONE
            }
        }
    }

    /**
     * Reset the views and values to prepare for a new workout
     */
    private fun resetViews(){
        lift1Visibility.value = View.INVISIBLE
        lift2Visibility.value = View.INVISIBLE
        lift3Visibility.value = View.INVISIBLE
        lift4Visibility.value = View.INVISIBLE
        lift5Visibility.value = View.INVISIBLE
        lift1Type.value = 0
        lift2Type.value = 0
        lift3Type.value = 0
        lift4Type.value = 0
        lift5Type.value = 0
        lift1Weight.value = null
        lift2Weight.value = null
        lift3Weight.value = null
        lift4Weight.value = null
        lift5Weight.value = null
        lift1Reps.value = null
        lift2Reps.value = null
        lift3Reps.value = null
        lift4Reps.value = null
        lift5Reps.value = null
        _startNewVisibility.value = View.VISIBLE
        _workoutButtonLayoutVisibility.value = View.INVISIBLE
        _newLiftButtonVisibility.value = View.INVISIBLE
        _finishButtonVisibility.value = View.INVISIBLE
    }

    /**
     * Returns the name of the lift to be inputted into the database when given a string
     * This corresponds to the number of the string array "lift_spinner" in the strings resource file
     */
    private fun getLiftName(liftType: Int): String?{
        when (liftType) {
            1 -> {
                return "BPress"
            }
            2 -> {
                return "OHP"
            }
            3 -> {
                return "ChinUp"
            }
            4 -> {
                return "BBCurl"
            }
            5 -> {
                return "Deadlift"
            }
            6 -> {
                return "Squat"
            }
        }
        return null
}

    /**
     * Finishes a workout by getting the date, inserting the values
     * for the workout into the "lifting_table" database table, then
     * calls resetViews() to prepare for a new workout.
     */
    fun finishWorkout(){
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateFormatted = "$year-$month-$day"

        insert(LiftingWorkoutModel(0,dateFormatted, lift1Type.value?.let { getLiftName(it) },lift1Weight.value,lift1Reps.value,
                lift2Type.value?.let { getLiftName(it) },lift2Weight.value,lift2Reps.value,
                lift3Type.value?.let { getLiftName(it) },lift3Weight.value,lift3Reps.value,
                lift4Type.value?.let { getLiftName(it) },lift4Weight.value,lift4Reps.value,
                lift5Type.value?.let { getLiftName(it) },lift5Weight.value,lift5Reps.value,))

        resetViews()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private fun insert(workout: LiftingWorkoutModel) = viewModelScope.launch {
        workoutRepository.insert(workout)
    }
}

//Create the view model factory for the activity class
class MainViewModelFactory(private val repository: WorkoutRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
