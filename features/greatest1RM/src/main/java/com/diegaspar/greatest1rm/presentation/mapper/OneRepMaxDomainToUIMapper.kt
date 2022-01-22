package com.diegaspar.greatest1rm.presentation.mapper

import com.diegaspar.greatest1rm.domain.model.OneRepMaxDomain
import com.diegaspar.greatest1rm.presentation.model.OneRepMaxUI

class OneRepMaxDomainToUIMapper {

    fun map(oneRepMaxDomain: OneRepMaxDomain) =
        OneRepMaxUI(
            date = oneRepMaxDomain.date,
            name = oneRepMaxDomain.name,
            oneRepMax = oneRepMaxDomain.oneRepMax
        )
}