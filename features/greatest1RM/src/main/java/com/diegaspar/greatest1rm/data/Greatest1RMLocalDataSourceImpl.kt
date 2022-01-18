package com.diegaspar.greatest1rm.data

import com.diegaspar.database_room.dao.OneRepMaxDao
import com.diegaspar.database_room.entinty.OneRepMaxEntity

class Greatest1RMLocalDataSourceImpl(private val dao: OneRepMaxDao) : Greatest1RMLocalDataSource {

    override suspend fun isEmpty() = dao.count() == 0
    override suspend fun loadOneRepMaxForAllExercisesOrderByName() =
        dao.loadOneRepMaxForAllExercisesOrderByName()

    override suspend fun loadAllByExerciseName(exerciseName: String) =
        dao.loadAllByExerciseName(exerciseName = exerciseName)

    override suspend fun insertAll(listOfExercises: List<OneRepMaxEntity>) =
        dao.insertAll(oneRepMaxList = listOfExercises)
}