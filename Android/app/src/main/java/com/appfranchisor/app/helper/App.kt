package com.appfranchisor.app.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import com.androidisland.vita.startVita

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        startVita()
        mInstance = this
    }

    companion object {
        val TAG = "AppTAG"
        @SuppressLint("StaticFieldLeak")
        var activeActivity: Activity? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var mInstance: App


        fun getContext(): Context? {
            return mInstance.applicationContext
        }

        fun getActivity():Activity?{
            Log.d(TAG, "getActivity: "+ activeActivity)
            return activeActivity
        }

    }


}