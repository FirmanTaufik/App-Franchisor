package com.appfranchisor.app.ui.aplikator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.AplikastorActivityDaftarFranchisorBinding

class AplikastorDaftarFranchisorActivity : AppCompatActivity() {
    lateinit var binding :AplikastorActivityDaftarFranchisorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AplikastorActivityDaftarFranchisorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}