package com.diegaspar.detailgreatest1rm.presentation.chart

import com.diegaspar.ui.utils.millisToDatePatternString
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class ChartXFormatter : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float)
            : String = millisToDatePatternString(value.toLong())
}