package com.appfranchisor.app.ui.franchisor.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchisorActivityInputProdukBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.addDropdown
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisor.FranchisorVM
import com.github.dhaval2404.imagepicker.ImagePicker

class FranchisorInputProdukActivity : MainActivity() {
    private var idKategori = 0
    private   var imageUpload :Uri ?=null
    private val viewmodel: FranchisorVM by viewModels ()
    private lateinit var binding :FranchisorActivityInputProdukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchisorActivityInputProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initKategori()
        initOnClick()
        onResult()
    }

    private fun initKategori() {
        viewmodel.getKategori().observe(this){
            when(it) {
                is ApiResponse.Success ->{
                    val list = it.item?.data?.map { it.nama }
                   binding.inputKategori.addDropdown(list as List<String>){ pos->
                       val data = it.item.data!![pos]
                       idKategori =data.id!!
                   }
                }
                else -> Unit
            }
        }
    }

    private fun onResult() {
        activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            if (resultCode == Activity.RESULT_OK) {
                val data = result.data
                val fileUri = data?.data!!
                imageUpload = fileUri
                binding.imageView.setImageURI(fileUri)
              /*
                val u1 = data?.data
                val extras = data?.extras
                val imageBitmap = extras!!["data"] as Bitmap?
                binding.imageView.setImageBitmap(imageBitmap)*/
//                imgPath = u1.toString()*//*
            }
        }
    }




    private fun initOnClick() {
        binding.apply {
            buttomSimpan.setOnClickListener {
               if ( validateInput()) {
                   showDialog(true)
                   viewmodel.postProduk(
                       PreferenceHelper.getUserId(this@FranchisorInputProdukActivity)!!,
                       idKategori, binding.inputNama.editText?.text.toString(),
                       binding.inputHarga.editText?.text.toString(),
                       imageUpload!!,
                   ).observe(this@FranchisorInputProdukActivity){
                       showDialog(false)
                       when(it) {
                           is ApiResponse.Success ->{
                               "sukses menambahkan".showAsToast()
                               finish()
                           }
                           is ApiResponse.Error ->{
                               it.message.showAsToast()
                           }
                           else -> Unit
                       }
                   }
               }
            }
            buttonGambar.setOnClickListener {
                Utils.checkPermission(this@FranchisorInputProdukActivity,list) {
                    openCamera()
                }
            }
        }
    }


    private fun validateInput(): Boolean {
        binding.apply {
            if (imageUpload == null) {
                "input gambar".showAsToast()
                return false
            }
            if (inputKategori.editText?.text.isNullOrEmpty()) {
                "input kategori".showAsToast()
                return false
            }
            if (inputNama.editText?.text.isNullOrEmpty()) {
                "input kategori".showAsToast()
                return false
            }
            if (inputHarga.editText?.text.isNullOrEmpty()) {
                "input kategori".showAsToast()
                return false
            }
        }
        return true
    }

    private fun showDialog(value:Boolean){
        binding.apply {
            if (value) {
                buttomSimpan.hide()
                progressBar.show()
            }else{
                buttomSimpan.show()
                progressBar.hide()
            }
        }
    }



}