package com.appfranchisor.app.ui.franchisee.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityDataMainBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.addDropdown
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.FranchiseeVM
import com.appfranchisor.app.ui.franchisor.FranchiseeModel

class FranchiseeDataMainActivity : MainActivity() {
    private lateinit var binding :FranchiseeActivityDataMainBinding
    private lateinit var viewModel : FranchiseeVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityDataMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[FranchiseeVM:: class.java]

        initUI()
        initOnClick()
    }

    private fun initUI() {
        viewModel.franchisee().observe(this) {
            when(it) {
                is ApiResponse.Success ->{
                    val data = it.item?.data!!.find { it.id == PreferenceHelper.getUserId(this) }
                    if (data != null) {
                        setUIEditText(data)
                    }
                }
                else ->Unit
            }
        }
    }
    private fun setUIEditText(data: FranchiseeModel.Data) {
        binding.apply {
            editTextUsername.setText( data.username)
            editTextPassword.setText( data.password)
            editTextPemilik.setText( data.pemilik)
            editTextAlamat.setText( data.alamat)
            editTextNomorTelpon.setText( data.nomorTelponOutlet.toString())
            editTextPic.setText( data.pic)
            editTextNomorPic.setText( data.nomorPic)
            editTextEmail.setText( data.email)
            editTextId.setText( data.id.toString())
        }
    }
    private fun initOnClick() {
        binding.apply {
            buttonBack.setOnClickListener { finish() }
            buttonSimpan.setOnClickListener {
               if (isValidateInput()) {
                    viewModel.updateUser(
                        PreferenceHelper.getUserId(this@FranchiseeDataMainActivity)!!,
                        editTextUsername.text.toString(),
                        editTextPassword.text.toString(),
                        editTextPemilik.text.toString(),
                        editTextEmail.text.toString(),
                        editTextAlamat.text.toString(),
                        editTextNomorTelpon.text.toString(),
                        editTextPic.text.toString(),
                        editTextNomorPic.text.toString()
                    ).observe(this@FranchiseeDataMainActivity){
                        when(it) {
                            is ApiResponse.Success ->{
                                binding.buttonSimpan.show()
                                binding.progressBar.hide()
                                val response = it.item!!
                                response.message?.showAsToast()
                                clearInput()
                                initUI()
                            }
                            is ApiResponse.Error ->{
                                it.message.showAsToast()
                                binding.buttonSimpan.show()
                                binding.progressBar.hide()
                            }
                            else ->{
                                binding.buttonSimpan.hide()
                                binding.progressBar.show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun clearInput() {
        binding.apply {

            editTextUsername.text.clear()
            editTextPassword.text.clear()
            editTextPemilik.text.clear()
            editTextAlamat.text.clear()
            editTextNomorTelpon.text.clear()
            editTextPic.text.clear()
            editTextNomorPic.text.clear()
            editTextEmail.text.clear()
        }

    }

    private fun isValidateInput(): Boolean {
        binding.apply {
            if (editTextUsername.text.isNullOrEmpty()) {
                "Pemilik Masih Kosong".showAsToast()
                return false
            }
            if (editTextPassword.text.isNullOrEmpty()) {
                "Pemilik Masih Kosong".showAsToast()
                return false
            }
            if (editTextPemilik.text.isNullOrEmpty()) {
                "Pemilik Masih Kosong".showAsToast()
                return false
            }
            if (editTextAlamat.text.isNullOrEmpty()) {
                "Alamat Masih Kosong".showAsToast()
                return false
            }
            if (editTextNomorTelpon.text.isNullOrEmpty()) {
                "Nomor Telpon Masih Kosong".showAsToast()
                return false
            }
            if (editTextPic.text.isNullOrEmpty()) {
                "PIC Masih Kosong".showAsToast()
                return false
            }
            if (editTextNomorPic.text.isNullOrEmpty()) {
                "Nomor PIC Masih Kosong".showAsToast()
                return false
            }
            if (editTextEmail.text.isNullOrEmpty()) {
                "Email Masih Kosong".showAsToast()
                return false
            }

        }

        return true
    }
}