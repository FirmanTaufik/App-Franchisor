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
        //initDataLineCart()
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
                            viewModel.pendapatan(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
                                today.toString(),yesterday.toString(), "day" )
                                .observe(this@FranchiseeDashboardActivity,  responsePendapatan)
                        }

                        R.id.week -> {
                            textSortPendapatan.text = "Weekly"
                            val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                            val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                            val mingguIni =  "$startOfWeek/$endOfWeek"
                            val mingguLalu = "${startOfWeek.minusDays(1)}/${startOfWeek.minusDays(7)} "
                            println("pendapatan $mingguIni $mingguLalu")

                            viewModel.pendapatan(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
                                mingguIni,mingguLalu, "week" )
                                .observe(this@FranchiseeDashboardActivity,  responsePendapatan)
                        }

                        R.id.month -> {
                            textSortPendapatan.text = "Month"
                            val yearMonth = YearMonth.from(today)
                            val startOfMonthThisMonth = yearMonth.atDay(1)
                            val endOfMonthThisMonth = yearMonth.atEndOfMonth()

                            // Mendapatkan objek YearMonth untuk bulan lalu
                            val yearMonthLastMonth = yearMonth.minusMonths(1)
                            val startOfMonthLastMonth = yearMonthLastMonth.atDay(1)
                            val endOfMonthLastMonth = yearMonthLastMonth.atEndOfMonth()

                            val day1 = "$startOfMonthThisMonth/$endOfMonthThisMonth"
                            val day2 = "$startOfMonthLastMonth/$endOfMonthLastMonth"
                            viewModel.pendapatan(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
                                day1,day2, "month" )
                                .observe(this@FranchiseeDashboardActivity,  responsePendapatan)
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

        val yesterday = today.minusDays(1)
        viewModel.pendapatan(PreferenceHelper.getUserId(this@FranchiseeDashboardActivity)!!,
            today.toString(),yesterday.toString(), "day" )
            .observe(this@FranchiseeDashboardActivity,  responsePendapatan)
    }






}



