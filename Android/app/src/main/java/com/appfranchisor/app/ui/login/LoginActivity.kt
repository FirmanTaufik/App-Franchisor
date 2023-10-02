package com.appfranchisor.app.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.ActivityLoginBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MasterVM
import com.appfranchisor.app.ui.aplikator.ui.AplikatorMainActivity
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.ui.FranchiseeMainActivity
import com.appfranchisor.app.ui.franchisor.ui.FranchisorMainActivity


class LoginActivity : MainActivity() {
    private lateinit var binding :ActivityLoginBinding
    private val viewModel : MasterVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        navigateToScreen()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
         initOnClick()
    }

    private fun initOnClick() {
         binding.buttonLogin.setOnClickListener {
             if (binding.editTextUername.text.toString().isNullOrEmpty()){
                 "Masukan Username dulu".showAsToast()
                 return@setOnClickListener
             }
             if (binding.editTextPassword.text.toString().isNullOrEmpty()){
                 "Masukan Password dulu".showAsToast()
                 return@setOnClickListener
             }
             postLogin(binding.editTextUername.text.toString(),binding.editTextPassword.text.toString())
         }
    }

    private fun postLogin(username: String, password: String) {
        viewModel.postLogin(username, password).observe(this){
            when(it) {
                is ApiResponse.Success ->{
                    binding.buttonLogin.show()
                    binding.progressBar.hide()
                    val response = it.item!!
                    response.message?.showAsToast()
                    PreferenceHelper.setUserId(this, response.userId)
                    PreferenceHelper.setToken(this, response.accessToken)
                    PreferenceHelper.setRole(this, response.role)
                    navigateToScreen()
                }
                is ApiResponse.Error ->{
                    it.message.showAsToast()
                    binding.buttonLogin.show()
                    binding.progressBar.hide()
                }
                else ->{
                    binding.buttonLogin.hide()
                    binding.progressBar.show()
                }
            }
        }
    }

    private fun navigateToScreen() {
       val token = PreferenceHelper.getToken(this)
        if (token!=null) {
            when(PreferenceHelper.getRole(this)) {
                1->startActivity(Intent(this, AplikatorMainActivity::class.java))
                2->startActivity(Intent(this, FranchisorMainActivity::class.java))
                3->startActivity(Intent(this, FranchiseeMainActivity::class.java))
            }
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