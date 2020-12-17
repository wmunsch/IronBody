package com.williammunsch.ironbody.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "cardio_table")
data class CardioWorkoutModel (

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "distance") val distance: Int,

    @ColumnInfo(name = "date") val date: String
)