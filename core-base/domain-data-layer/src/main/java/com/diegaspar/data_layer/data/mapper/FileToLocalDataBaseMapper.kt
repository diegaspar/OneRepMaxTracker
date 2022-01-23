package com.diegaspar.data_layer.data.mapper

import com.diegaspar.asset.model.WorkOutFromFile
import com.diegaspar.database_room.entinty.OneRepMaxEntity
import com.diegaspar.onerepmax.OneRepMaxCalculator

class FileToLocalDataBaseMapper(private val oneRepMaxCalculator: OneRepMaxCalculator) {
    fun map(workOutFromFile: WorkOutFromFile): OneRepMaxEntity =
        OneRepMaxEntity(
            date = workOutFromFile.date,
            name = workOutFromFile.exerciseName,
            reps = workOutFromFile.reps,
            weight = workOutFromFile.weight,
            oneRepMax = oneRepMaxCalculator.calculateWithBrzyckiFormula(
                reps = workOutFromFile.reps.toFloat(),
                weight = workOutFromFile.weight
            ).toInt()
        )
}