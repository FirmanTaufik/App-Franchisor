package com.appfranchisor.app.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface GetService {

    @GET("api/internal/info-api/getip")
    fun getIp( ): Call<String>



}