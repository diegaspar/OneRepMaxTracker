package com.diegaspar.database_room.entinty

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["date", "exercise_name", "reps", "weight"])
data class OneRepMaxEntity(
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "exercise_name") val name: String,
    @ColumnInfo(name = "reps") val reps: Int,
    @ColumnInfo(name = "weight") val weight: Float,
    @ColumnInfo(name = "one_rep_max") val oneRepMax: Int,
)
