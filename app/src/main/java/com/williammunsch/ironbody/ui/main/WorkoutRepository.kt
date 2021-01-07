package com.williammunsch.ironbody.ui.main

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.williammunsch.ironbody.room.WorkoutDao
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import com.williammunsch.ironbody.room.entities.WeightModel
import kotlinx.coroutines.flow.Flow

/**
 * The repository for the entire app.
 * Handles transferring date between the view model and ROOM
 */
class WorkoutRepository(private val workoutDao: WorkoutDao) {
    //The list of the most recent 250 workout lifts
    val historyData: Flow<List<LiftingWorkoutModel>> = workoutDao.getLiftingWorkouts()

    //Retrieving the most recent body weight entry
    private val weightData: LiveData<WeightModel> = workoutDao.getMostRecentWeight()
    val weight = Transformations.map(weightData){ _ ->
        weightData.value?.body_weight
    }

    //Current/most recent workout weights for each type and the transforms to retrieve the Int
    private val benchLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getBenchCurrent()
    private val OHPLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getOHPCurrent()
    private val chinUpLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getChinUpCurrent()
    private val BBCurlLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getBBCurlCurrent()
    private val deadliftLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getDeadCurrent()
    private val squatLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getSquatCurrent()

    val benchCurrent = Transformations.map(benchLiftCurrent){ _ ->
        benchLiftCurrent.value?.weight
    }
    val OHPCurrent = Transformations.map(OHPLiftCurrent){ _ ->
        OHPLiftCurrent.value?.weight
    }
    val chinUpCurrent = Transformations.map(chinUpLiftCurrent){ _ ->
        chinUpLiftCurrent.value?.weight
    }
    val BBCurlCurrent = Transformations.map(BBCurlLiftCurrent){ _ ->
        BBCurlLiftCurrent.value?.weight
    }
    val deadliftCurrent = Transformations.map(deadliftLiftCurrent){ _ ->
        deadliftLiftCurrent.value?.weight
    }
    val squatCurrent = Transformations.map(squatLiftCurrent){ _ ->
        squatLiftCurrent.value?.weight
    }


    //Max workout weights and transforms to retrieve the Int
    private val benchLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getBenchMax()
    private val OHPLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getOHPMax()
    private val chinUpLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getChinUpMax()
    private val BBCurlLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getBBCurlMax()
    private val deadliftLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getDeadMax()
    private val squatLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getSquatMax()

    val benchMax = Transformations.map(benchLiftMax){ _ ->
        benchLiftMax.value?.weight
    }
    val OHPMax = Transformations.map(OHPLiftMax){ _ ->
        OHPLiftMax.value?.weight
    }
    val chinUpMax = Transformations.map(chinUpLiftMax){ _ ->
        chinUpLiftMax.value?.weight
    }
    val BBCurlMax = Transformations.map(BBCurlLiftMax){ _ ->
        BBCurlLiftMax.value?.weight
    }
    val deadliftMax = Transformations.map(deadliftLiftMax){ _ ->
        deadliftLiftMax.value?.weight
    }
    val squatMax = Transformations.map(squatLiftMax){ _ ->
        squatLiftMax.value?.weight
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weightModel: WeightModel){
        workoutDao.insert(weightModel)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(liftingWorkoutModel: LiftingWorkoutModel){
        workoutDao.insert(liftingWorkoutModel)
    }


}