package com.williammunsch.ironbody.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "lifting_table")
data class LiftingWorkoutModel(

        @PrimaryKey(autoGenerate = true) val id: Int,

        @ColumnInfo(name = "lift_name") val name: String,

        @ColumnInfo(name = "sets") val sets: String,

        @ColumnInfo(name = "weight") val weight: String,

        @ColumnInfo(name = "repetitions")  val repetitions: String,

        @ColumnInfo(name = "date") val date: Date)