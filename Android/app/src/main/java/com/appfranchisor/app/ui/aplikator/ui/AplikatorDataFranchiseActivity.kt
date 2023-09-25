package com.appfranchisor.app.ui.aplikator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.AplikatorActivityDataFranchiseBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.aplikator.AplikatorVM

class AplikatorDataFranchiseActivity : MainActivity() {
    private val viewmodel: AplikatorVM by viewModels ()
    private lateinit var binding :AplikatorActivityDataFranchiseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =AplikatorActivityDataFranchiseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnClick()
    }

    private fun initOnClick() {
        binding.apply {
            buttonSimpan.setOnClickListener {
                if (isValidateInput()) {
                    viewmodel.postUser(
                        editTextPemilik.text.toString(),
                        editTextAlamat.text.toString(),
                        editTextNomorTelpon.text.toString(),
                        editTextPic.text.toString(),
                        editTextNomorPic.text.toString(),
                        editTextEmail.text.toString()
                    ).observe(this@AplikatorDataFranchiseActivity){
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