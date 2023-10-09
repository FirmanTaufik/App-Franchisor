package com.appfranchisor.app.api

import com.appfranchisor.app.data.FilterOrderModel
import com.appfranchisor.app.data.KategoriModel
import com.appfranchisor.app.data.ResponseModel
import com.appfranchisor.app.ui.aplikator.FranchisorModel
import com.appfranchisor.app.ui.franchisee.model.OrderModel
import com.appfranchisor.app.ui.franchisee.model.PesanProduk
import com.appfranchisor.app.ui.franchisor.FranchiseeModel
import com.appfranchisor.app.ui.login.LoginModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
        @Field("id_aplikator") id_aplikator: Int?,
        @Field("username") username: String?,
        @Field("password") password: String?,
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
        @Field("id_aplikator") id_aplikator: Int?,
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("pemilik") nama: String?,
        @Field("email") email: String?,
        @Field("alamat") alamat: String?,
        @Field("nomor_telpon_outlet") nomor_telpon_outlet: String?,
        @Field("pic") pic: String?,
        @Field("nomor_pic") nomor_pic: String?,
    ) : Response<ResponseModel>

    @GET("franchisor")
    suspend fun franchisor( ) : Response<FranchisorModel>

    //Franchisee
    @FormUrlEncoded
    @POST("createFranchisee")
    suspend fun createFranchisee(
        @Field("id_franchisor") id_franchisor: Int?,
        @Field("username") username: String?,
        @Field("password") password: String?,
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
        @Field("id_franchisor") id_franchisor: Int?,
        @Field("username") username: String?,
        @Field("password") password: String?,
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
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("pemilik") nama: String?,
        @Field("email") email: String?,
        @Field("alamat") alamat: String?,
        @Field("nomor_telpon_outlet") nomor_telpon_outlet: String?,
        @Field("pic") pic: String?,
        @Field("nomor_pic") nomor_pic: String?,
    ) : Response<ResponseModel>

    @GET("franchisee")
    suspend fun franchisee( ) : Response<FranchiseeModel>

    @POST("logout")
    suspend fun postLogout( ) : Response<String>

    @GET("kategori")
    suspend fun getkategori(): Response<KategoriModel>

    @Multipart
    @POST("createProduk")
    suspend fun postProduk(
        @Part("id_franchisor") id_franchisor: RequestBody?,
        @Part("id_kategori") id_kategori: RequestBody?,
        @Part("nama") nama: RequestBody?,
        @Part("harga") harga: RequestBody?,
        @Part image: MultipartBody.Part?
    ): Response<ResponseModel>



    @GET("pesanProduk")
    suspend fun pesanProduk(): Response<PesanProduk>


    @FormUrlEncoded
    @POST("createOrder")
    suspend fun createOrder(
        @Field("tanggal") tanggal: String?,
        @Field("id_franchisee") username: Int?,
        @Field("nama_pembeli") nama_pembeli: String?,
        @Field("no_hp") no_hp: String?,
        @Field("alamat") alamat: String?,
        @Field("status") status: Int?,
        @Field("cart") cart: String?
    ): Response<String>

    @GET("order/{id}")
    suspend fun getOrder(@Path("id") id: Int?,
                      ): Response<OrderModel>


    @FormUrlEncoded
    @POST("updateStatusOrder/{id}")
    suspend fun updateStatusOrder(
        @Path("id") id: Int?,
        @Field("status") status: Int?,
    ): Response<String>



    @GET("filterOrder")
    suspend fun filterOrder(  ): Response<FilterOrderModel>

    @GET("filterOrder")
    suspend fun filterOrder(
        @Query("dari") dari: String?,
        @Query("hingga") hingga: String?,
        ): Response<FilterOrderModel>


    @GET("terlaris/{id}")
    suspend fun terlaris(
        @Path("id") id: Int?,
        @Query("dari") dari: String?,
        @Query("hingga") hingga: String?,
    ): Response<FilterOrderModel>
}