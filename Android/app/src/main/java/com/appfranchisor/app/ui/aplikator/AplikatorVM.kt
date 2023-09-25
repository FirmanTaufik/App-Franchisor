package com.appfranchisor.app.ui.aplikator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.api.ApiService
import com.appfranchisor.app.helper.Utils.convertErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class AplikatorVM  @Inject constructor(private val apiService: ApiService) : ViewModel() {

    fun franchisor(
    )= flow {
        emit(ApiResponse.Loading)
        val response = apiService.franchisor(  )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)


    fun updateUser(
        id:Int,
        pemilik: String,
        alamat: String,
        nomorTelpon: String,
        pic: String,
        nomorPIC: String,
        email: String
    )= flow {
        emit(ApiResponse.Loading)
        val response = apiService.updateFranchisor(
            id,  pemilik, email, alamat, nomorTelpon,  pic, nomorPIC
        )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun postUser(
        pemilik: String,
        alamat: String,
        nomorTelpon: String,
        pic: String,
        nomorPIC: String,
        email: String
    )= flow {
        emit(ApiResponse.Loading)
        val response = apiService.createFranchisor(
            pemilik, email, alamat, nomorTelpon,  pic, nomorPIC
        )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)


}