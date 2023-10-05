package com.appfranchisor.app.ui.franchisee.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchiseeActivityInputTransaksiBinding
import com.appfranchisor.app.ui.MainActivity

class FranchiseeInputTransaksiActivity : MainActivity() {
    private lateinit var binding:FranchiseeActivityInputTransaksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityInputTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}