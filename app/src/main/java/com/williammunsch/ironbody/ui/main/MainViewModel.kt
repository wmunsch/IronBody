package com.williammunsch.ironbody.ui.main

import android.view.View
import androidx.lifecycle.*
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import com.williammunsch.ironbody.room.entities.WeightModel
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * The view model for the entire app.
 */
class MainViewModel(private val workoutRepository: WorkoutRepository) : ViewModel() {

    val historyListData: LiveData<List<LiftingWorkoutModel>> = workoutRepository.historyData.asLiveData()

    val bodyWeight:LiveData<Int?> = workoutRepository.weight

    val benchCurrent:LiveData<Int?> = workoutRepository.benchCurrent
    val OHPCurrent:LiveData<Int?> = workoutRepository.OHPCurrent
    val chinUpCurrent:LiveData<Int?> = workoutRepository.chinUpCurrent
    val BBCurlCurrent:LiveData<Int?> = workoutRepository.BBCurlCurrent
    val deadliftCurrent:LiveData<Int?> = workoutRepository.deadliftCurrent
    val squatCurrent:LiveData<Int?> = workoutRepository.squatCurrent

    val benchMax:LiveData<Int?> = workoutRepository.benchMax
    val OHPMax:LiveData<Int?> = workoutRepository.OHPMax
    val chinUpMax:LiveData<Int?> = workoutRepository.chinUpMax
    val BBCurlMax:LiveData<Int?> = workoutRepository.BBCurlMax
    val deadliftMax:LiveData<Int?> = workoutRepository.deadliftMax
    val squatMax:LiveData<Int?> = workoutRepository.squatMax



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

    private val _addWeightVisibility = MutableLiveData<Int>()
    val addWeightVisibility: LiveData<Int>
        get() = _addWeightVisibility

    //String that holds body weight input
    var bodyWeightInput = MutableLiveData<String>()

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
        _workoutButtonLayoutVisibility.value = View.GONE
        _newLiftButtonVisibility.value = View.GONE
        _finishButtonVisibility.value = View.GONE
        _addWeightVisibility.value = View.GONE
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

    fun startNewWeightInput(){
        _startNewVisibility.value = View.GONE
        _addWeightVisibility.value = View.VISIBLE

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
        val date = c.timeInMillis

        if (lift1Type.value?.let {getLiftName(it)}!=null && lift1Weight.value != null && lift1Reps.value != null) {
            insert(LiftingWorkoutModel(0,date,lift1Type.value?.let {getLiftName(it)}, lift1Weight.value!!.toInt() ,lift1Reps.value))
        }
        if (lift2Type.value?.let {getLiftName(it)}!=null && lift2Weight.value != null && lift2Reps.value != null) {
            insert(LiftingWorkoutModel(0,date,lift2Type.value?.let {getLiftName(it)}, lift2Weight.value!!.toInt() ,lift2Reps.value))
        }
        if (lift3Type.value?.let {getLiftName(it)}!=null && lift3Weight.value != null && lift3Reps.value != null) {
            insert(LiftingWorkoutModel(0,date,lift3Type.value?.let {getLiftName(it)}, lift3Weight.value!!.toInt() ,lift3Reps.value))
        }
        if (lift4Type.value?.let {getLiftName(it)}!=null && lift4Weight.value != null && lift4Reps.value != null) {
            insert(LiftingWorkoutModel(0,date,lift4Type.value?.let {getLiftName(it)}, lift4Weight.value!!.toInt() ,lift4Reps.value))
        }
        if (lift5Type.value?.let {getLiftName(it)}!=null && lift5Weight.value != null && lift5Reps.value != null) {
            insert(LiftingWorkoutModel(0,date,lift5Type.value?.let {getLiftName(it)}, lift5Weight.value!!.toInt() ,lift5Reps.value))
        }

        resetViews()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private fun insert(workout: LiftingWorkoutModel) = viewModelScope.launch {
        workoutRepository.insert(workout)
    }


    fun setNewWeight(){
        val c = Calendar.getInstance()
        val date = c.timeInMillis

        if (bodyWeightInput.value != null){
            insert(WeightModel(0,date,bodyWeightInput.value!!.toInt()))
        }

        bodyWeightInput.value = null
        _addWeightVisibility.value = View.GONE
        _startNewVisibility.value = View.VISIBLE
    }

    private fun insert(weightModel: WeightModel) = viewModelScope.launch {
        workoutRepository.insert(weightModel)
    }

    fun cancelWeightInput(){
        bodyWeightInput.value = null
        _addWeightVisibility.value = View.GONE
        _startNewVisibility.value = View.VISIBLE
    }
}

//Create the view model factory
class MainViewModelFactory(private val repository: WorkoutRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
