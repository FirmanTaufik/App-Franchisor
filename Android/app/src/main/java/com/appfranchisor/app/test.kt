package com.appfranchisor.app

import java.time.LocalDate
import java.util.Calendar
import java.util.Date

fun main(){
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
}







fun tambahTujuhHari(tanggal: LocalDate): LocalDate {
    return tanggal.plusDays(7)
}
