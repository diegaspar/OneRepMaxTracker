package com.diegaspar.data_layer.data

import com.diegaspar.database_room.entinty.OneRepMaxEntity

interface Greatest1RMLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun loadOneRepMaxForAllExercisesOrderByName(): List<OneRepMaxEntity>
    suspend fun loadAllByExerciseName(exerciseName: String): List<OneRepMaxEntity>
    suspend fun insertAll(listOfExercises: List<OneRepMaxEntity>)
}