package com.appfranchisor.app.ui.franchisor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchisorActivityDashboardBinding
import com.appfranchisor.app.helper.MpChartActivity
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.addDropdown
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

class FranchisorDashboardActivity : MpChartActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            viewModel.pendapatan( idFranchisee,
                                today.toString(),yesterday.toString(), "day" )
                                .observe(this@FranchisorDashboardActivity,  responsePendapatan)
                        }

                        R.id.week -> {
                            textSortPendapatan.text = "Weekly"
                            val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                            val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                            val mingguIni =  "$startOfWeek/$endOfWeek"
                            val mingguLalu = "${startOfWeek.minusDays(1)}/${startOfWeek.minusDays(7)} "
                            println("pendapatan $mingguIni $mingguLalu")

                            viewModel.pendapatan(idFranchisee,
                                mingguIni,mingguLalu, "week" )
                                .observe(this@FranchisorDashboardActivity,  responsePendapatan)
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
                            viewModel.pendapatan(idFranchisee!!,
                                day1,day2, "month" )
                                .observe(this@FranchisorDashboardActivity,  responsePendapatan)
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
                            viewModel.terlaris(
                                idFranchisee,
                                today.toString() , "null" )
                                .observe(this@FranchisorDashboardActivity, responseTerlaris)
                        }

                        R.id.week -> {
                            textSortTerlaris.text = "Weekly"
                            val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                            val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                            viewModel.terlaris(
                                idFranchisee,
                                startOfWeek.toString() , endOfWeek.toString() )
                                .observe(this@FranchisorDashboardActivity, responseTerlaris)
                        }

                        R.id.month -> {
                            textSortTerlaris.text = "Month"
                            val yearMonth = YearMonth.from(today)
                            val startOfMonth = yearMonth.atDay(1)
                            val endOfMonth = yearMonth.atEndOfMonth()
                            viewModel.terlaris(
                                idFranchisee,
                                startOfMonth.toString() , endOfMonth.toString() )
                                .observe(this@FranchisorDashboardActivity, responseTerlaris)
                        }

                    }
                }
            }
        }
    }

    private fun initUI() {
        viewModel.franchisee(PreferenceHelper.getUserId(this)).observe(this) {
            when (it) {
                is ApiResponse.Success -> {
                    listFranchisee = it.item!!.data.toList()
                    val list = it.item.data.map { it.pemilik!! }
                    binding.inputFranchisee.addDropdown(list) {
                        idFranchisee =  listFranchisee[it].id!!
                        initStartData()
                    }
                    binding.inputFranchisee.editText?.setText(listFranchisee.first().pemilik)
                    idFranchisee = listFranchisee.first().id!!
                    initStartData()
                }

                else -> Unit
            }
        }
    }

    private fun initStartData() {
        binding.textSortTerlaris.text = "Today"
        binding.textSortPendapatan.text = "Today"
        val today = LocalDate.now()
        viewModel.terlaris(
            idFranchisee!!,
            today.toString() , "null" )
            .observe(this@FranchisorDashboardActivity, responseTerlaris)

        val yesterday = today.minusDays(1)
        viewModel.pendapatan(
            idFranchisee,
            today.toString(),yesterday.toString(), "day" )
            .observe(this@FranchisorDashboardActivity,  responsePendapatan)
    }

}