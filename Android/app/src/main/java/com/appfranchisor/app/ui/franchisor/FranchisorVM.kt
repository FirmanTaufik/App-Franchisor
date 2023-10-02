package com.appfranchisor.app.ui.franchisor

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.api.ApiService
import com.appfranchisor.app.helper.App
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.convertErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class FranchisorVM  @Inject constructor(private val apiService: ApiService) : ViewModel() {


    fun postProduk(id_franchisor:Int, id_kategori :Int,
                   nama:String, harga:String, imageUri :Uri) = flow {
        emit(ApiResponse.Loading)

        val file = java.io.File(Utils.getRealPathFromURIPath(imageUri, App.getContext()!!))
        val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body: MultipartBody.Part = MultipartBody.Part.createFormData("gambar", file.name, requestFile)

        val idFranchisor = id_franchisor.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val idKategori = id_kategori.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val nama = nama.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val harga = harga.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        val response = apiService.postProduk(idFranchisor,  idKategori,nama,harga, body )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))
    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun getKategori() = flow {
        emit(ApiResponse.Loading)
        val response = apiService.getkategori(  )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))
    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun franchisee(
    )= flow {
        emit(ApiResponse.Loading)
        val response = apiService.franchisee(  )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)


    fun updateUser(
        id:Int,
        id_franchisor: Int,
        username: String,
        password: String,
        pemilik: String,
        email: String,
        alamat: String,
        nomorTelpon: String,
        pic: String,
        nomorPIC: String
    )= flow {
        emit(ApiResponse.Loading)
        val response = apiService.updateFranchisee(
            id,  id_franchisor, username, password, pemilik, email, alamat, nomorTelpon,  pic, nomorPIC

        )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun postUser(
        id_franchisor: Int,
        username: String,
        password: String,
        pemilik: String,
        email: String,
        alamat: String,
        nomorTelpon: String,
        pic: String,
        nomorPIC: String
    )= flow {
        emit(ApiResponse.Loading)
        val response = apiService.createFranchisee(
            id_franchisor, username, password, pemilik, email, alamat, nomorTelpon,  pic, nomorPIC
        )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)


}