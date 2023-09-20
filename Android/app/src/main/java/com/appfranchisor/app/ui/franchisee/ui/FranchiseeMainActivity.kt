package com.appfranchisor.app.ui.franchisee.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}