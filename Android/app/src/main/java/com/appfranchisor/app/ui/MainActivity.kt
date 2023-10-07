package com.appfranchisor.app.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {
    var list  = arrayListOf(Manifest.permission.CAMERA)
      var activityLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListPermission()
    }



    private fun initListPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            list.add(Manifest.permission.BLUETOOTH_CONNECT)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            list.add(Manifest.permission.READ_MEDIA_IMAGES)
            list.add(Manifest.permission.READ_MEDIA_VIDEO)
            list.add(Manifest.permission.READ_MEDIA_AUDIO)
        }else{
            list.add(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            list.add(
                Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

      fun openCamera(){
        ImagePicker.with(this)
            .compress(1024)
            .crop()//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                activityLauncher?.launch(intent)
            }
    }



    /*private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
           *//* val data: Intent? = result.data
            val u1 = data.data
            val extras = data.extras
            val imageBitmap = extras!!["data"] as Bitmap?
            imgPath = u1.toString()*//*
        }
    }*/


}