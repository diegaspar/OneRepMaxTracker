package com.diegaspar.greatest1rm.domain

import com.diegaspar.asset.data.WorkOutFileDataSource
import com.diegaspar.greatest1rm.data.Greatest1RMLocalDataSource
import com.diegaspar.greatest1rm.data.mapper.FileToLocalDataBaseMapper
import com.diegaspar.greatest1rm.domain.mapper.LocalDataBaseToDomainMapper
import com.diegaspar.greatest1rm.domain.model.OneRepMaxDomain

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

    private suspend fun checkDataBaseIsEmpty() {
        if (localDataSource.isEmpty()) {
            localDataSource.insertAll(
                fileDataSource.extractWorkoutDataFromFile()
                    .map { fileToLocalDataBaseMapper.map(it) })
        }
    }

}