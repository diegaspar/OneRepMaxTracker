package com.diegaspar.greatest1rm.domain

import com.diegaspar.greatest1rm.domain.model.OneRepMaxDomain

interface Greatest1RMRepository {
    suspend fun getOneRepMaxGroupedByExercise(): List<OneRepMaxDomain>
}