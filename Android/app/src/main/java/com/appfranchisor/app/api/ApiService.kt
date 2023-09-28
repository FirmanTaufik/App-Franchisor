package com.appfranchisor.app.api

import com.appfranchisor.app.ui.ResponseModel
import com.appfranchisor.app.ui.aplikator.FranchisorModel
import com.appfranchisor.app.ui.franchisor.FranchiseeModel
import com.appfranchisor.app.ui.login.LoginModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    @GET("getByTag/{id}")
//    fun getByTag(
//        @Path("id") id: Int,
//        @Query("page") page: Int?=0, ): Observable<TagFilmModel>

   @FormUrlEncoded
   @POST("login")
    suspend fun postLogin(
       @Field("username") username: String?,
       @Field("password") password: String?
    ) : Response<LoginModel>

    //franchisor
    @FormUrlEncoded
    @POST("createFranchisor")
    suspend fun createFranchisor(
        @Field("pemilik") nama: String?,
        @Field("email") email: String?,
        @Field("alamat") alamat: String?,
        @Field("nomor_telpon_outlet") nomor_telpon_outlet: String?,
        @Field("pic") pic: String?,
        @Field("nomor_pic") nomor_pic: String?,
    ) : Response<ResponseModel>

    @FormUrlEncoded
    @POST("updateFranchisor/{id}")
    suspend fun updateFranchisor(
        @Path("id") id: Int,
        @Field("pemilik") nama: String?,
        @Field("email") email: String?,
        @Field("alamat") alamat: String?,
        @Field("nomor_telpon_outlet") nomor_telpon_outlet: String?,
        @Field("pic") pic: String?,
        @Field("nomor_pic") nomor_pic: String?, ) : Response<ResponseModel>

    @GET("franchisor")
    suspend fun franchisor( ) : Response<FranchisorModel>

    //Franchisee
    @FormUrlEncoded
    @POST("createFranchisee")
    suspend fun createFranchisee(
        @Field("pemilik") nama: String?,
        @Field("email") email: String?,
        @Field("alamat") alamat: String?,
        @Field("nomor_telpon_outlet") nomor_telpon_outlet: String?,
        @Field("pic") pic: String?,
        @Field("nomor_pic") nomor_pic: String?,
    ) : Response<ResponseModel>

    @FormUrlEncoded
    @POST("updateFranchisee/{id}")
    suspend fun updateFranchisee(
        @Path("id") id: Int,
        @Field("pemilik") nama: String?,
        @Field("email") email: String?,
        @Field("alamat") alamat: String?,
        @Field("nomor_telpon_outlet") nomor_telpon_outlet: String?,
        @Field("pic") pic: String?,
        @Field("nomor_pic") nomor_pic: String?, ) : Response<ResponseModel>

    @GET("franchisee")
    suspend fun franchisee( ) : Response<FranchiseeModel>

    @POST("logout")
    suspend fun postLogout( ) : Response<String>
}