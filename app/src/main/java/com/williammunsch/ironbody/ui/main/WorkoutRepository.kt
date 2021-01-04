package com.williammunsch.ironbody.ui.main

import androidx.annotation.WorkerThread
import com.williammunsch.ironbody.room.WorkoutDao
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import kotlinx.coroutines.flow.Flow

/**
 * The repository for the entire app.
 * Handles transferring date between the view model and ROOM
 */
class WorkoutRepository( val workoutDao: WorkoutDao) {

    val allLiftingWorkouts: Flow<List<LiftingWorkoutModel>> = workoutDao.getLiftingWorkouts()
    val benchMax5: Flow<String> = workoutDao.getBenchMax5()




    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(liftingWorkoutModel: LiftingWorkoutModel){
        workoutDao.insert(liftingWorkoutModel)
    }


}