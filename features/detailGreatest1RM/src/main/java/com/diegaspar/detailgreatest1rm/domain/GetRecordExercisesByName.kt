package com.diegaspar.detailgreatest1rm.domain

import com.diegaspar.data_layer.repo.Greatest1RMRepository

class GetRecordExercisesByName(
    private val repository: Greatest1RMRepository
) {
    suspend fun invoke(params: Params) =
        repository.getOneRepMaxRecordByExerciseName(params.exerciseName)

    data class Params(val exerciseName: String)
}