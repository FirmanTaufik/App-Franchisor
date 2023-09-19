package com.appfranchisor.app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.appfranchisor.app.databinding.ActivityLoginBinding
import com.appfranchisor.app.ui.franchisee.ui.FranchiseeMainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
         initOnClick()
    }

    private fun initOnClick() {
         binding.buttonLogin.setOnClickListener { startActivity(Intent(this, FranchiseeMainActivity::class.java)) }
    }


}