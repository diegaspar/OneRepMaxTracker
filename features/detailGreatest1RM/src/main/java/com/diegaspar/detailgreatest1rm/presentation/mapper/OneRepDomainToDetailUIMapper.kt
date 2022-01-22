package com.diegaspar.detailgreatest1rm.presentation.mapper

import com.diegaspar.data_layer.model.OneRepMaxDomain
import com.diegaspar.detailgreatest1rm.presentation.model.OneRepDetail

class OneRepDomainToDetailUIMapper {

    fun map(oneRepMaxDomain: OneRepMaxDomain) =
        OneRepDetail(
            name = oneRepMaxDomain.name,
            date = oneRepMaxDomain.date,
            oneRepMax = oneRepMaxDomain.oneRepMax
        )
}