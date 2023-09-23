package com.appfranchisor.app.api

import com.appfranchisor.app.ui.login.LoginModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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


    @POST("logout")
    suspend fun postLogout( ) : Response<String>
}