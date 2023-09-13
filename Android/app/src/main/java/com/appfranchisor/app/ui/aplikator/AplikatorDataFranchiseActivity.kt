package com.appfranchisor.app.ui.aplikator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.AplikatorActivityDataFranchiseBinding

class AplikatorDataFranchiseActivity : AppCompatActivity() {
    private lateinit var binding :AplikatorActivityDataFranchiseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =AplikatorActivityDataFranchiseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}