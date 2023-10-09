package com.appfranchisor.app.ui.franchisee.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.data.FilterOrderModel
import com.appfranchisor.app.helper.MpChartActivity
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.showAsToast
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

class FranchiseeDashboardActivity : MpChartActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataLineCart()
        initUI()
        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            textSortPendapatan.setOnClickListener {
                val today = LocalDate.now()
                showPopUp(it) {
                    when (it) {
                        R.id.day -> {
                            textSortPendapatan.text = "Today"
                            val yesterday = today.minusDays(1)
                            println("pendapatan $today $yesterday")
                        }

                        R.id.week -> {
                            textSortPendapatan.text = "Weekly"
                            val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                            val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                            val mingguIni =  "$startOfWeek $endOfWeek"
                            val mingguLalu = "${startOfWeek.minusDays(1)} ${startOfWeek.minusDays(7)} "

                            println("pendapatan $mingguIni $mingguLalu")
                        }

                        R.id.month -> {
                            textSortPendapatan.text = "Month"
                            // Mendapatkan objek YearMonth untuk bulan saat ini
                            val yearMonth = YearMonth.from(today)

                            // Mendapatkan tanggal awal bulan ini
                            val startOfMonthThisMonth = yearMonth.atDay(1)

                            // Mendapatkan tanggal akhir bulan ini
                            val endOfMonthThisMonth = yearMonth.atEndOfMonth()

                            // Mendapatkan objek YearMonth untuk bulan lalu
                            val yearMonthLastMonth = yearMonth.minusMonths(1)

                            // Mendapatkan tanggal awal bulan lalu
                            val startOfMonthLastMonth = yearMonthLastMonth.atDay(1)

                            // Mendapatkan tanggal akhir bulan lalu
                            val endOfMonthLastMonth = yearMonthLastMonth.atEndOfMonth()

                            // Mencetak tanggal awal dan akhir bulan ini
                            println("Tanggal awal bulan ini: $startOfMonthThisMonth")
                            println("Tanggal akhir bulan ini: $endOfMonthThisMonth")

                            // Mencetak tanggal awal dan akhir bulan lalu
                            println("Tanggal awal bulan lalu: $startOfMonthLastMonth")
                            println("Tanggal akhir bulan lalu: $endOfMonthLastMonth")
                        }

                    }
                }
            }

            textSortTerlaris.setOnClickListener {
                val today = LocalDate.now()
                showPopUp(it) {
                    when (it) {
                        R.id.day -> {
                            textSortTerlaris.text = "Today"
                            viewModel.terlaris(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
                                today.toString() , "null" )
                                .observe(this@FranchiseeDashboardActivity, responseTerlaris)
                        }

                        R.id.week -> {
                            textSortTerlaris.text = "Weekly"
                            val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                            val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                            viewModel.terlaris(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
                                startOfWeek.toString() , endOfWeek.toString() )
                                .observe(this@FranchiseeDashboardActivity, responseTerlaris)
                        }

                        R.id.month -> {
                            textSortTerlaris.text = "Month"
                            val yearMonth = YearMonth.from(today)
                            val startOfMonth = yearMonth.atDay(1)
                            val endOfMonth = yearMonth.atEndOfMonth()
                            viewModel.terlaris(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
                                startOfMonth.toString() , endOfMonth.toString() )
                                .observe(this@FranchiseeDashboardActivity, responseTerlaris)
                        }

                    }
                }
            }
        }
    }


    private fun initUI() {
        binding.inputFranchisee.hide()
        val today = LocalDate.now()
        viewModel.terlaris(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
            today.toString() , "null" )
            .observe(this@FranchiseeDashboardActivity, responseTerlaris)
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





}



