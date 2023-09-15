package com.appfranchisor.app.helper

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.MpchartActivityBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

open class MpChartActivity : AppCompatActivity() {

    private lateinit var binding : MpchartActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= MpchartActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUIHorizontalBar( )
        setUpLineChart()
    }

    private fun setUpLineChart() {
        with(binding.lineChart) {
            animateX(1200, Easing.EaseInSine)
            description.isEnabled = false

            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F
            xAxis.valueFormatter = MyAxisFormatter()

            axisRight.isEnabled = false
            extraRightOffset = 30f

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 12F
            legend.form = Legend.LegendForm.LINE
        }
    }

    fun setDataToLineChart(week1: ArrayList<Entry>, week2: ArrayList<Entry>) {

        val weekOneSales = LineDataSet(week1, "Week 1")
        weekOneSales.lineWidth = 3f
        weekOneSales.valueTextSize = 15f
        weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekOneSales.color = ContextCompat.getColor(this, R.color.colorGray)
        weekOneSales.valueTextColor = ContextCompat.getColor(this, R.color.colorGray)
       // weekOneSales.enableDashedLine(20F, 10F, 0F)

        val weekTwoSales = LineDataSet(week2, "Week 2")
        weekTwoSales.lineWidth = 3f
        weekTwoSales.valueTextSize = 15f
        weekTwoSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekTwoSales.color = ContextCompat.getColor(this, R.color.blueLinceCart)
        weekTwoSales.valueTextColor = ContextCompat.getColor(this, R.color.blueLinceCart)
     //   weekTwoSales.enableDashedLine(20F, 10F, 0F)


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)
        dataSet.add(weekTwoSales)

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        binding.lineChart.invalidate()
    }

    private fun initUIHorizontalBar(){
        val skillRatingChart = binding.horizontalChart              //skill_rating_chart is the id of the XML layout

        skillRatingChart.setDrawBarShadow(false)
        val description = Description()
        description.text = ""
        skillRatingChart.description = description
        skillRatingChart.legend.isEnabled = false
        skillRatingChart.setPinchZoom(false)
        skillRatingChart.setDrawValueAboveBar(false)

        //Display the axis on the left (contains the labels 1*, 2* and so on)
        val xAxis = skillRatingChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isEnabled = true
        xAxis.setDrawAxisLine(false)


        val yLeft = skillRatingChart.axisLeft

//Set the minimum and maximum bar lengths as per the values that they represent
        yLeft.axisMaximum = 100f
        yLeft.axisMinimum = 0f
        yLeft.isEnabled = false

        //Set label count to 5 as we are displaying 5 star rating
        xAxis.setLabelCount(5)

//Now add the labels to be added on the vertical axis
        val values = arrayOf("Ayam Bakar", "Ayam Goreng", "Ayam Moza", "Kulit Ayam", "Ayam Geprek")
        xAxis.valueFormatter = XAxisValueFormatter(values)
        val yRight = skillRatingChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = false

        //Add animation to the graph
        skillRatingChart.animateY(2000)
    }

      fun setDataHorizontalBar(entries: ArrayList<BarEntry>) {

        //Note : These entries can be replaced by real-time data, say, from an API
        val barDataSet = BarDataSet(entries, "Bar Data Set")

        binding.horizontalChart  .setDrawBarShadow(true)
        barDataSet.barShadowColor = Color.argb(40, 150, 150, 150)
        barDataSet.color = Color.GREEN
        val data = BarData(barDataSet)

        binding.horizontalChart.getXAxis().setLabelCount(entries.size, false);
        //Set the bar width
        //Note : To increase the spacing between the bars set the value of barWidth to < 1f
        data.barWidth = 0.9f

        //Finally set the data and refresh the graph
        binding.horizontalChart  .data = data
        binding.horizontalChart  .invalidate()
    }
}