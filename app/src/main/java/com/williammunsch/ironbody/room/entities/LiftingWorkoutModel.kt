package com.williammunsch.ironbody.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "lifting_table")
data class LiftingWorkoutModel(

        @PrimaryKey(autoGenerate = true) val id: Int,

        @ColumnInfo(name = "date") val date: Long,

        @ColumnInfo(name = "lift_name") val lift_name: String?,

        @ColumnInfo(name = "weight") val weight: Int,

        @ColumnInfo(name = "repetitions")  val repetitions: String?,
/*
        @ColumnInfo(name = "lift_name2") val lift_name2: String?,

        @ColumnInfo(name = "weight2") val weight2: String?,

        @ColumnInfo(name = "repetitions2")  val repetitions2: String?,

        @ColumnInfo(name = "lift_name3") val lift_name3: String?,

        @ColumnInfo(name = "weight3") val weight3: String?,

        @ColumnInfo(name = "repetitions3")  val repetitions3: String?,

        @ColumnInfo(name = "lift_name4") val lift_name4: String?,

        @ColumnInfo(name = "weight4") val weight4: String?,

        @ColumnInfo(name = "repetitions4")  val repetitions4: String?,

        @ColumnInfo(name = "lift_name5") val lift_name5: String?,

        @ColumnInfo(name = "weight5") val weight5: String?,

        @ColumnInfo(name = "repetitions5")  val repetitions5: String?
        */

)