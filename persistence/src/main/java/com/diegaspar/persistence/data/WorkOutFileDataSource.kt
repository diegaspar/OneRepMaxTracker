package com.diegaspar.persistence.data

import com.diegaspar.persistence.model.WorkOutFromFile

interface WorkOutFileDataSource {
    suspend fun extractWorkoutDataFromFile(): List<WorkOutFromFile>
}