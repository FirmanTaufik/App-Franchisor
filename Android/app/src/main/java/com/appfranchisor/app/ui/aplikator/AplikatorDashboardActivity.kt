package com.appfranchisor.app.ui.aplikator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.AplikatorActivityDashboardBinding

class AplikatorDashboardActivity : AppCompatActivity() {
    private lateinit var binding :AplikatorActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=AplikatorActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}