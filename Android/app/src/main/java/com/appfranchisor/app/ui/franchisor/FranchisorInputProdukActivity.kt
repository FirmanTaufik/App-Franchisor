package com.appfranchisor.app.ui.franchisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchisorActivityInputProdukBinding

class FranchisorInputProdukActivity : AppCompatActivity() {
    private lateinit var binding :FranchisorActivityInputProdukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchisorActivityInputProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}