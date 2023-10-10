package com.appfranchisor.app.ui.franchisor.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchisorActivityMainBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.MasterVM
import com.appfranchisor.app.ui.TransaksiActivity
import com.appfranchisor.app.ui.login.LoginActivity

class FranchisorMainActivity : MainActivity() {
    private lateinit var   viewModel : MasterVM
    lateinit var binding : FranchisorActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FranchisorActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[MasterVM:: class.java]
        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            buttonDaftarFranchisee.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDaftarFranchiseeActivity::class.java) )}
            buttonDataDranchisee.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDataFranchiseeActivity::class.java)) }
            buttonDashboard.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDashboardActivity::class.java)) }
            buttonInputProduk.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorInputProdukActivity::class.java)) }
            buttonTransaksiFranchisee.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, TransaksiActivity::class.java))  }
            daftarProduk.setOnClickListener { startActivity(Intent(this@FranchisorMainActivity, FranchisorDaftarProdukActivity::class.java))  }
            buttonLogout.setOnClickListener {
                Utils.showDialogDefaultConfirmation(this@FranchisorMainActivity, title = "Butuh Konfirmasi",
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