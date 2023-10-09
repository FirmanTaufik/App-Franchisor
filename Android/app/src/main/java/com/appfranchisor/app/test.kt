package com.appfranchisor.app

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters
import java.util.Calendar
import java.util.Date

/*fun main(){
    println("Masukkan tanggal awal (yyyy-MM-dd): ")
    val tanggalAwalStr = readLine() ?: ""

    try {
        val tanggalAwal = LocalDate.parse(tanggalAwalStr)
        val tanggalAkhir =  tanggalAwal.plusDays(7)
      //  val tanggalAkhir = tambahTujuhHari(tanggalAwal)

        println("Tanggal awal: $tanggalAwal")
        println("Tanggal setelah ditambah 7 hari: $tanggalAkhir")
    } catch (e: Exception) {
        println("Format tanggal tidak valid. Gunakan format yyyy-MM-dd.")
    }
}*/

fun main(){
    val today = LocalDate.now()

    // Mendapatkan objek YearMonth untuk bulan saat ini
    val yearMonth = YearMonth.from(today)

    // Mendapatkan tanggal awal bulan saat ini
    val startOfMonth = yearMonth.atDay(1)

    // Mendapatkan tanggal akhir bulan saat ini
    val endOfMonth = yearMonth.atEndOfMonth()

    // Mencetak tanggal awal dan akhir bulan saat ini
    println("Tanggal awal bulan ini: $startOfMonth")
    println("Tanggal akhir bulan ini: $endOfMonth")
}

/*fun main(){
    val today = LocalDate.now()

    // Mendapatkan tanggal awal minggu saat ini
    val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

    // Mendapatkan tanggal akhir minggu saat ini
    val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))

    // Mencetak tanggal awal dan akhir minggu saat ini
    println("Tanggal awal minggu ini: $startOfWeek")
    println("Tanggal akhir minggu ini: $endOfWeek")
}*/







