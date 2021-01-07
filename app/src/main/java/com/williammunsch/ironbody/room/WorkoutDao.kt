package com.williammunsch.ironbody.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import com.williammunsch.ironbody.room.entities.WeightModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM lifting_table ORDER BY date DESC LIMIT 250")
    fun getLiftingWorkouts(): Flow<List<LiftingWorkoutModel>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: LiftingWorkoutModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weight: WeightModel)


    @Query("SELECT * FROM weight_table ORDER BY date DESC LIMIT 1")
    fun getMostRecentWeight(): LiveData<WeightModel>


    @Query("SELECT * FROM lifting_table WHERE lift_name = 'BPress' ORDER BY date DESC LIMIT 1")
    fun getBenchCurrent(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'OHP' ORDER BY date DESC LIMIT 1")
    fun getOHPCurrent(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'ChinUp' ORDER BY date DESC LIMIT 1")
    fun getChinUpCurrent(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'BBCurl' ORDER BY date DESC LIMIT 1")
    fun getBBCurlCurrent(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'Deadlift' ORDER BY date DESC LIMIT 1")
    fun getDeadCurrent(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'Squat' ORDER BY date DESC LIMIT 1")
    fun getSquatCurrent(): LiveData<LiftingWorkoutModel>


    @Query("SELECT * FROM lifting_table WHERE lift_name = 'BPress' ORDER BY weight DESC LIMIT 1")
    fun getBenchMax(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'OHP' ORDER BY weight DESC LIMIT 1")
    fun getOHPMax(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'ChinUp' ORDER BY weight DESC LIMIT 1")
    fun getChinUpMax(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'BBCurl' ORDER BY weight DESC LIMIT 1")
    fun getBBCurlMax(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'Deadlift' ORDER BY weight DESC LIMIT 1")
    fun getDeadMax(): LiveData<LiftingWorkoutModel>

    @Query("SELECT * FROM lifting_table WHERE lift_name = 'Squat' ORDER BY weight DESC LIMIT 1")
    fun getSquatMax(): LiveData<LiftingWorkoutModel>


}