package com.appfranchisor.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MasterVM  @Inject constructor(private val apiService: ApiService) : ViewModel() {

    fun pendapatan( id:Int,day1 :String, day2 :String , jenis :String) = flow {
        emit(ApiResponse.Loading)
        val response = apiService.pendapatan(id,day1, day2, jenis)
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))
    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun terlaris( id:Int,dari :String, hingga :String ) = flow {
        emit(ApiResponse.Loading)
        val response = apiService.terlaris(id,dari, hingga)
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun filterOrder( dari :String?=null, hingga :String?=null ) = flow {
        emit(ApiResponse.Loading)
        val response = if (dari==null && hingga ==null)
            apiService.filterOrder()
        else  apiService.filterOrder(dari, hingga)
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)




    fun postLogout( ) = flow {
        emit(ApiResponse.Loading)
        val response = apiService.postLogout()
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun postLogin(username:String, password:String) = flow {
        emit(ApiResponse.Loading)
        val response = apiService.postLogin(username, password)
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "Username atau Password Salah"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)
}