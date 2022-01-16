package com.diegaspar.persistence.data

import android.content.Context
import com.diegaspar.persistence.model.WorkOutFromFile


const val workOutDataFileName = "workoutData.txt"

class WorkOutFileDataSourceImpl(private val context: Context) : WorkOutFileDataSource {
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun extractWorkoutDataFromFile(): List<WorkOutFromFile> {
        val workOutList = mutableListOf<WorkOutFromFile>()
        context.assets.open(workOutDataFileName).bufferedReader().useLines { lines ->
            lines.forEach {
                val listOfItems = it.split(",")
                workOutList.add(
                    WorkOutFromFile(
                        date = listOfItems.firstOrNull() ?: "",
                        exerciseName = listOfItems.getOrNull(1) ?: "",
                        reps = listOfItems.getOrNull(2)?.toIntOrNull() ?: 0,
                        weight = listOfItems.getOrNull(3)?.toFloatOrNull() ?: 0F
                    )
                )
            }
        }
        return workOutList
    }

}

