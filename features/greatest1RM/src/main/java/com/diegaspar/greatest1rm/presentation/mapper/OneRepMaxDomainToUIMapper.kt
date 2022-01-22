package com.diegaspar.greatest1rm.presentation.mapper

import com.diegaspar.data_layer.model.OneRepMaxDomain
import com.diegaspar.greatest1rm.presentation.model.OneRepMaxUI

class OneRepMaxDomainToUIMapper {

    fun map(oneRepMaxDomain: OneRepMaxDomain) =
        OneRepMaxUI(
            name = oneRepMaxDomain.name,
            oneRepMax = oneRepMaxDomain.oneRepMax
        )
}