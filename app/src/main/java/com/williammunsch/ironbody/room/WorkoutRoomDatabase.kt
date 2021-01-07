package com.williammunsch.ironbody.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.williammunsch.ironbody.room.entities.LiftingWorkoutModel
import com.williammunsch.ironbody.room.entities.WeightModel

@Database(entities = [LiftingWorkoutModel::class, WeightModel::class], version = 1, exportSchema = false)
public abstract class WorkoutRoomDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE: WorkoutRoomDatabase? = null

        fun getDatabase(context: Context): WorkoutRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutRoomDatabase::class.java,
                    "workout_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}