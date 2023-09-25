package com.appfranchisor.app.ui.aplikator.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.AplikastorActivityDaftarFranchisorBinding
import com.appfranchisor.app.helper.Utils.addDropdown
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.aplikator.AplikatorVM
import com.appfranchisor.app.ui.aplikator.FranchisorModel

class AplikastorDaftarFranchisorActivity : MainActivity() {
    lateinit var binding :AplikastorActivityDaftarFranchisorBinding
    private val viewmodel:AplikatorVM by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AplikastorActivityDaftarFranchisorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initOnClick()
    }

    private fun initUI() {
        viewmodel.franchisor().observe(this) {
            when(it) {
                is ApiResponse.Success ->{
                    val list = it.item!!.data.map { it.pemilik }
                    binding.inputFranchisor.addDropdown(list as List<String>){ pos->
                        val data = it.item.data[pos]
                        setUIEditText(data)
                    }
                }
                else ->Unit
            }
        }
    }

    private fun setUIEditText(data: FranchisorModel.Data) {
        binding.apply {
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
            buttonSimpan.setOnClickListener {
                if (isValidateInput()) {
                    viewmodel.updateUser(
                        editTextId.text.toString().toInt(),
                        editTextPemilik.text.toString(),
                        editTextAlamat.text.toString(),
                        editTextNomorTelpon.text.toString(),
                        editTextPic.text.toString(),
                        editTextNomorPic.text.toString(),
                        editTextEmail.text.toString()
                    ).observe(this@AplikastorDaftarFranchisorActivity){
                        when(it) {
                            is ApiResponse.Success ->{
                                binding.buttonSimpan.show()
                                binding.progressBar.hide()
                                val response = it.item!!
                                response.message?.showAsToast()
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