package com.appfranchisor.app.ui.franchisee.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityMainBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.MasterVM
import com.appfranchisor.app.ui.login.LoginActivity

class FranchiseeMainActivity : MainActivity() {
    private lateinit var binding :FranchiseeActivityMainBinding
    private lateinit var   viewModel : MasterVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =FranchiseeActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[MasterVM:: class.java]
        initOnclick()
    }

    private fun initOnclick() {
        binding.apply {
            buttonInputDataTransaksi.setOnClickListener { startActivity(Intent(this@FranchiseeMainActivity, FranchiseeInputTransaksiActivity::class.java))  }
            buttonStatusPesanan.setOnClickListener { startActivity(Intent(this@FranchiseeMainActivity, FranchiseeStatusPesananActivity::class.java))  }
            buttonDataFranchisee.setOnClickListener { startActivity(Intent(this@FranchiseeMainActivity, FranchiseeDataMainActivity::class.java))  }
            buttonDashboard.setOnClickListener { startActivity(Intent(this@FranchiseeMainActivity, FranchiseeDashboardActivity::class.java))  }

            buttonLogout.setOnClickListener {
                Utils.showDialogDefaultConfirmation(this@FranchiseeMainActivity, title = "Butuh Konfirmasi",
                    message =  "Apakah Yakin Mau logout?",
                    onNegative = {},
                    onPositive = {
                        postLogOut()
                    })
            }
        }

    }
    private fun postLogOut() {
        viewModel.postLogout().observe(this) {
            when(it){
                is ApiResponse.Success->{
                    "Sukses Logout".showAsToast()
                    PreferenceHelper.clearAllPreference(this)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is ApiResponse.Error ->{
                    it.message.showAsToast()
                }
                else ->Unit
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