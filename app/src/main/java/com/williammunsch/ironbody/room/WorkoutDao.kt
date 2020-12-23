package com.williammunsch.ironbody.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.williammunsch.ironbody.room.entities.CardioWorkoutModel
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM lifting_table ORDER BY date ASC")
    fun getLiftingWorkouts(): Flow<List<LiftingWorkoutModel>>

    @Query("SELECT * FROM cardio_table ORDER BY date ASC")
    fun getCardioWorkouts(): Flow<List<CardioWorkoutModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: LiftingWorkoutModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: CardioWorkoutModel)

    @Query("SELECT weight FROM lifting_table WHERE lift_name = 'bench_press' ORDER BY weight ASC LIMIT 1")
    fun getBenchMax5(): Flow<String>



}