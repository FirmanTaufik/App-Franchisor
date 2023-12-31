package com.appfranchisor.app.ui.aplikator.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.AplikatorActivityInputFranchisorBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.aplikator.AplikatorVM

class AplikatorInputFranchisorActivity : MainActivity() {
    private val viewmodel: AplikatorVM by viewModels ()
    private lateinit var binding :AplikatorActivityInputFranchisorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =AplikatorActivityInputFranchisorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            buttonSimpan.setOnClickListener {
                if (isValidateInput()) {
                    viewmodel.postUser(
                        PreferenceHelper.getUserId(this@AplikatorInputFranchisorActivity)!!,
                        editTextUsername.text.toString(),
                        editTextPassword.text.toString(),
                        editTextPemilik.text.toString(),
                        editTextEmail.text.toString(),
                        editTextAlamat.text.toString(),
                        editTextNomorTelpon.text.toString(),
                        editTextPic.text.toString(),
                        editTextNomorPic.text.toString()
                    ).observe(this@AplikatorInputFranchisorActivity){
                        when(it) {
                            is ApiResponse.Success ->{
                                binding.buttonSimpan.show()
                                binding.progressBar.hide()
                                val response = it.item!!
                                response.message?.showAsToast()
                                clearInput()
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