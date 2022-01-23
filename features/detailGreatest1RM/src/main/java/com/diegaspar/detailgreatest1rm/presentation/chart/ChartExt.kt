package com.diegaspar.detailgreatest1rm.presentation.chart

import android.content.Context
import com.diegaspar.detailgreatest1rm.R
import com.diegaspar.ui.utils.provideSafeColor
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet

fun LineChart.setupStyleProperties(context: Context?) {
    this.apply {
        data = lineData
        legend.isEnabled = false
        description.isEnabled = false
        setTouchEnabled(false)
        axisRight.apply {
            setDrawLabels(false)
        }
        axisLeft.apply {
            textSize = 12f
            textColor = provideSafeColor(context, R.color.white)
            setDrawAxisLine(true)
            setDrawGridLines(true)
            valueFormatter = ChartYFormatter(resources.getString(R.string.lbs))
        }
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 12f
            textColor = provideSafeColor(context, R.color.white)
            setDrawAxisLine(false)
            setDrawGridLines(true)
            valueFormatter = ChartXFormatter()
        }
        setGridBackgroundColor(provideSafeColor(context, R.color.dark))
        setBackgroundColor(provideSafeColor(context, R.color.dark))
    }
}


fun LineDataSet.setupStyleProperties(context: Context?) {
    this.apply {
        lineWidth = 2f
        circleRadius = 4f
        color = provideSafeColor(context, R.color.red)
        setCircleColor(provideSafeColor(context, R.color.red))
        circleHoleColor = provideSafeColor(context, R.color.red)
        setDrawValues(false)
    }
}