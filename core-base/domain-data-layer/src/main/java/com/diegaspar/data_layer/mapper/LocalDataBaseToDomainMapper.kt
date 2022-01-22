package com.diegaspar.data_layer.mapper

import com.diegaspar.data_layer.model.OneRepMaxDomain
import com.diegaspar.database_room.entinty.OneRepMaxEntity

class LocalDataBaseToDomainMapper {
    fun map(localDatabaseEntity: OneRepMaxEntity): OneRepMaxDomain =
        OneRepMaxDomain(
            date = localDatabaseEntity.date,
            name = localDatabaseEntity.name,
            oneRepMax = localDatabaseEntity.oneRepMax
        )
}