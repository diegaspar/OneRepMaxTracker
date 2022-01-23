package com.diegaspar.detailgreatest1rm.presentation.mapper

import com.diegaspar.data_layer.model.OneRepMaxDomain
import com.diegaspar.ui.utils.dateStringToMillis
import com.github.mikephil.charting.data.Entry

class OneRepDomainToDetailUIMapper {

    fun map(oneRepMaxDomain: OneRepMaxDomain) =
        Entry(
            dateStringToMillis(oneRepMaxDomain.date).toFloat(),
            oneRepMaxDomain.oneRepMax.toFloat(),
        )
}