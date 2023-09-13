package com.appfranchisor.app.ui.aplikator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.AplikatorActivityMainBinding

class AplikatorMainActivity : AppCompatActivity() {
    private lateinit var binding :AplikatorActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AplikatorActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            buttonDashboard.setOnClickListener { startActivity(Intent(this@AplikatorMainActivity, AplikatorDashboardActivity::class.java)) }
            buttonDaftarFranchiseer.setOnClickListener { startActivity(Intent(this@AplikatorMainActivity, AplikatorDataFranchiseActivity::class.java)) }
            buttonDaftarFranchisor.setOnClickListener { startActivity(Intent(this@AplikatorMainActivity, AplikastorDaftarFranchisorActivity::class.java)) }

        }
    }
}