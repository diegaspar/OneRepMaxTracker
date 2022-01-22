package com.diegaspar.data_layer.repo

import com.diegaspar.asset.data.WorkOutFileDataSource
import com.diegaspar.data_layer.data.Greatest1RMLocalDataSource
import com.diegaspar.data_layer.data.mapper.FileToLocalDataBaseMapper
import com.diegaspar.data_layer.mapper.LocalDataBaseToDomainMapper
import com.diegaspar.data_layer.model.OneRepMaxDomain

class Greatest1RMRepositoryImpl(
    private val localDataSource: Greatest1RMLocalDataSource,
    private val fileDataSource: WorkOutFileDataSource,
    private val fileToLocalDataBaseMapper: FileToLocalDataBaseMapper,
    private val localDataBaseToDomainMapper: LocalDataBaseToDomainMapper
) : Greatest1RMRepository {

    override suspend fun getOneRepMaxGroupedByExercise(): List<OneRepMaxDomain> {
        checkDataBaseIsEmpty()
        return localDataSource.loadOneRepMaxForAllExercisesOrderByName()
            .map { localDataBaseToDomainMapper.map(it) }
    }

    override suspend fun getOneRepMaxRecordByExerciseName(name: String) =
        localDataSource.loadAllByExerciseName(exerciseName = name)
            .map { localDataBaseToDomainMapper.map(it) }

    private suspend fun checkDataBaseIsEmpty() {
        if (localDataSource.isEmpty()) {
            localDataSource.insertAll(
                fileDataSource.extractWorkoutDataFromFile()
                    .map { fileToLocalDataBaseMapper.map(it) })
        }
    }

}