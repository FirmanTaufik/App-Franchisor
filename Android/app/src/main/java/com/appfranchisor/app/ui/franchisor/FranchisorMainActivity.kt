package com.appfranchisor.app.ui.franchisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchisorActivityMainBinding

class FranchisorMainActivity : AppCompatActivity() {
    lateinit var binding : FranchisorActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FranchisorActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}