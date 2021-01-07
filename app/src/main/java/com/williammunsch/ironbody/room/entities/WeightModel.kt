package com.williammunsch.ironbody.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

/**
 * Model for the table that holds body weight data.
 */
@Entity(tableName = "weight_table")
data class WeightModel (
        @PrimaryKey(autoGenerate = true) val id: Int,

        @ColumnInfo(name = "date") val date: Long,

        @ColumnInfo(name = "body_weight") val body_weight: Int

)