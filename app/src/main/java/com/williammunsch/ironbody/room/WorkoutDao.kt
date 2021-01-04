package com.williammunsch.ironbody.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM lifting_table ORDER BY date ASC")
    fun getLiftingWorkouts(): Flow<List<LiftingWorkoutModel>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: LiftingWorkoutModel)


    @Query("SELECT weight1 FROM lifting_table WHERE lift_name1 = 'bench_press' ORDER BY weight1 ASC LIMIT 1")
    fun getBenchMax5(): Flow<String>



}