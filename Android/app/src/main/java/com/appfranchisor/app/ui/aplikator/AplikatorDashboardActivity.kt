package com.appfranchisor.app.ui.aplikator

import android.os.Bundle
import com.appfranchisor.app.helper.MpChartActivity
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry


class AplikatorDashboardActivity : MpChartActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initDataHorizontalBar()
        initDataLineCart()
    }

    private fun initDataLineCart() {
        setDataToLineChart(week1(), week2())
    }
    private fun week1(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 15f))
        sales.add(Entry(1f, 16f))
        sales.add(Entry(2f, 13f))
        sales.add(Entry(3f, 22f))
        sales.add(Entry(4f, 20f))
        return sales
    }

    private fun week2(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 11f))
        sales.add(Entry(1f, 13f))
        sales.add(Entry(2f, 18f))
        sales.add(Entry(3f, 16f))
        sales.add(Entry(4f, 22f))
        return sales
    }

    private fun initDataHorizontalBar() {
        //Add a list of bar entries
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 27f))
        entries.add(BarEntry(1f, 45f))
        entries.add(BarEntry(2f, 65f))
        entries.add(BarEntry(3f, 77f))
        entries.add(BarEntry(4f, 93f))
        setDataHorizontalBar(entries)
    }


}