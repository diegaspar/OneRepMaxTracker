package com.diegaspar.detailgreatest1rm.presentation.chart

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class ChartYFormatter(private val stringLbs: String) : IndexAxisValueFormatter() {
    override fun getFormattedValue(value: Float)
            : String = "${value.toLong()} $stringLbs"
}