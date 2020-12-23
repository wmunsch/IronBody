package com.williammunsch.ironbody

import android.app.Application
import com.williammunsch.ironbody.room.WorkoutRoomDatabase
import com.williammunsch.ironbody.ui.main.WorkoutRepository

class WorkoutApplication : Application() {
    val database by lazy {WorkoutRoomDatabase.getDatabase(this)}
    val repository by lazy {WorkoutRepository(database.workoutDao())}

}