package com.appfranchisor.app.helper

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

  class MyAxisFormatter(val items: ArrayList<String?>?) : IndexAxisValueFormatter() {


    override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
        val index = value.toInt()
        return if (index < items!!.size) {
            items[index]
        } else {
            null
        }
    }
}