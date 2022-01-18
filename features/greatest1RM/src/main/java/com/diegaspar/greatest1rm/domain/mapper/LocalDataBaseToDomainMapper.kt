package com.diegaspar.greatest1rm.domain.mapper

import com.diegaspar.database_room.entinty.OneRepMaxEntity
import com.diegaspar.greatest1rm.domain.model.OneRepMaxDomain

class LocalDataBaseToDomainMapper {
    fun map(localDatabaseEntity: OneRepMaxEntity): OneRepMaxDomain =
        OneRepMaxDomain(
            date = localDatabaseEntity.date,
            name = localDatabaseEntity.name,
            oneRepMax = localDatabaseEntity.oneRepMax
        )
}