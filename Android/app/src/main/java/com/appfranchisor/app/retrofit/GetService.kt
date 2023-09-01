package com.app.legatranskurir.retrofit

import com.app.legatranskurir.ui.login.model.LoginModel
import com.app.legatranskurir.ui.profil.model.LogoutModel
import com.app.legatranskurir.ui.register.RegisterModel
import com.app.legatranskurir.ui.requestpickup.model.CekResiModel
import com.app.legatranskurir.ui.requestpickup.model.RequestPickupModel
import com.app.legatranskurir.ui.riwayatterimabarang.model.RiwayatTerimaBarangModel
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface GetService {

    @GET("api/internal/info-api/getip")
    fun getIp( ): Call<String>


    @GET("api/internal/info-api/waktuserver")
    fun getWaktuServer( ): Call<String>

    @GET("android/version.txt")
    fun getVersion( ): Call<String>

    @GET("android/V-1-0-0-7-D-S.apk")
    fun downloadApk( ): Call<String>


    @HTTP(method = "POST", path = "api/internal/kurir-api/login", hasBody = true)
    fun login(@Body body: JsonObject): Call<LoginModel>


    @HTTP(method = "POST", path = "api/internal/kurir-api/logout", hasBody = true)
    fun logout(@Body body: JsonObject ): Call<String>


    @HTTP(method = "POST", path = "api/internal/kurir-api/register", hasBody = true)
    fun register( @Body body: JsonObject): Call<RegisterModel>


    @HTTP(method = "POST", path = "api/internal/req-pickup-api/getreq", hasBody = true)
    fun getReq(@Body body: JsonObject ): Call<RequestPickupModel>


    @HTTP(method = "POST", path = "api/internal/req-pickup-api/updatestatus", hasBody = true)
    fun updatestatus( @Body body: JsonObject): Call<String>


    @HTTP(method = "POST", path = "api/internal/trans-api/regeneratedb", hasBody = true)
    fun regenerateDb( @Body body: JsonObject): Call<LoginModel>


    @HTTP(method = "POST", path = "api/internal/trans-api/gettranskurir", hasBody = true)
    fun gettranskurir( @Body body: JsonObject): Call<String>


    @HTTP(method = "POST", path = "api/internal/trans-api/getresi", hasBody = true)
    fun getresi(@Body body: JsonObject ): Call<String>


    @HTTP(method = "POST", path = "api/internal/trans-api/getreq", hasBody = true)
    fun getreq( @Body body: JsonObject): Call<String>

    @HTTP(method = "POST", path = "api/internal/trans-api/listbykurir", hasBody = true)
    fun listbykurir( @Body body: JsonObject): Call<String>


    @HTTP(method = "POST", path = "api/internal/trans-api/listbykurirdone", hasBody = true)
    fun listbykurirdone(@Body body: JsonObject ): Call<RiwayatTerimaBarangModel>

    @HTTP(method = "POST", path = "api/internal/trans-api/updateterimabarang", hasBody = true)
    fun updateterimabarang(@Body body: String ): Call<String>


    @POST("api/internal/trans-api/uploadfotopenerima")
    @Multipart
    fun uploadfotopenerima(@Part("no_resi") no_resi:RequestBody, @Part("petugas") petugas:RequestBody,
                           @Part image: MultipartBody.Part ): Call<String>

    @HTTP(method = "POST", path = "api/internal/trans-api/insertresiedc", hasBody = true)
    fun insertresiedc(@Body body: String): Call<String>


    @HTTP(method = "POST", path = "api/internal/kurir-api/changepassword", hasBody = true)
    fun updateKataSandi(@Body body: JsonObject): Call<String>

}