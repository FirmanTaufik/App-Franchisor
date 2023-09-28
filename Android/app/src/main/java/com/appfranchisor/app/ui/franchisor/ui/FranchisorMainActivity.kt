package com.appfranchisor.app.ui.franchisor.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchisorActivityMainBinding
import com.appfranchisor.app.ui.MainActivity

class FranchisorMainActivity : MainActivity() {
    lateinit var binding : FranchisorActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FranchisorActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            buttonDaftarFranchisee.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDaftarFranchiseeActivity::class.java) )}
            buttonDataDranchisee.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDataFranchiseeActivity::class.java)) }
            buttonDashboard.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDashboardActivity::class.java)) }
            buttonInputProduk.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorInputProdukActivity::class.java)) }

        }
    }

    var doubleBackToExitPressedOnce=false
    @SuppressLint("NewApi")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tap Sekali Lagi Untuk Keluar", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}