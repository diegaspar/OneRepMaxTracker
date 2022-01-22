package com.diegaspar.data_layer.repo

import com.diegaspar.data_layer.model.OneRepMaxDomain

interface Greatest1RMRepository {
    suspend fun getOneRepMaxGroupedByExercise(): List<OneRepMaxDomain>
    suspend fun getOneRepMaxRecordByExerciseName(name: String): List<OneRepMaxDomain>
}