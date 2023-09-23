package com.appfranchisor.app.ui.franchisee.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.appfranchisor.app.databinding.FranchiseeActivityMainBinding

class FranchiseeMainActivity : AppCompatActivity() {
    private lateinit var binding :FranchiseeActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =FranchiseeActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOnclick()
    }

    private fun initOnclick() {
        binding.apply {
            buttonPesanProduk.setOnClickListener { startActivity(Intent(this@FranchiseeMainActivity, FranchiseeMenuActivity::class.java))  }
            buttonStatusPesanan.setOnClickListener { startActivity(Intent(this@FranchiseeMainActivity, FranchiseeStatusPesananActivity::class.java))  }
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