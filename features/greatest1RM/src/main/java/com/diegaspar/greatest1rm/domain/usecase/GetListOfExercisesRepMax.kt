package com.diegaspar.greatest1rm.domain.usecase

import com.diegaspar.data_layer.repo.Greatest1RMRepository

class GetListOfExercisesRepMax(
    private val repository: Greatest1RMRepository
) {
    suspend fun invoke() = repository.getOneRepMaxGroupedByExercise()
}