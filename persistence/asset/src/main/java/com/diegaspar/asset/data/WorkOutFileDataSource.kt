package com.diegaspar.asset.data

import com.diegaspar.asset.model.WorkOutFromFile

interface WorkOutFileDataSource {
    suspend fun extractWorkoutDataFromFile(): List<WorkOutFromFile>
}