package com.williammunsch.ironbody.ui.main

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.williammunsch.ironbody.room.WorkoutDao
import com.williammunsch.ironbody.room.entities.CardioWorkoutModel
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import kotlinx.coroutines.flow.Flow

class WorkoutRepository( val workoutDao: WorkoutDao) {

    val allLiftingWorkouts: Flow<List<LiftingWorkoutModel>> = workoutDao.getLiftingWorkouts()
    val allCardioWorkouts: Flow<List<CardioWorkoutModel>> = workoutDao.getCardioWorkouts()
    val benchMax5: Flow<String> = workoutDao.getBenchMax5()



    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(liftingWorkoutModel: LiftingWorkoutModel){
        workoutDao.insert(liftingWorkoutModel)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cardioWorkoutModel: CardioWorkoutModel){
        workoutDao.insert(cardioWorkoutModel)
    }
}