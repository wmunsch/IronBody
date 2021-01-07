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

    val historyData: Flow<List<LiftingWorkoutModel>> = workoutDao.getLiftingWorkouts()


    private val weightData: LiveData<WeightModel> = workoutDao.getMostRecentWeight()

    val weight = Transformations.map(weightData){ _ ->
        weightData.value?.body_weight
    }

    //Current
    val benchLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getBenchCurrent()
    val OHPLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getOHPCurrent()
    val chinUpLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getChinUpCurrent()
    val BBCurlLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getBBCurlCurrent()
    val deadliftLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getDeadliftCurrent()
    val squatLiftCurrent: LiveData<LiftingWorkoutModel> = workoutDao.getSquatCurrent()

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
        BBCurlLiftCurrent.value?.weight
    }
    val squatCurrent = Transformations.map(squatLiftCurrent){ _ ->
        squatLiftCurrent.value?.weight
    }


    //Max
    val benchLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getBenchMax()
    val OHPLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getOHPMax()
    val chinUpLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getChinUpMax()
    val BBCurlLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getBBCurlMax()
    val deadliftLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getDeadliftMax()
    val squatLiftMax: LiveData<LiftingWorkoutModel> = workoutDao.getSquatMax()

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
        BBCurlLiftMax.value?.weight
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