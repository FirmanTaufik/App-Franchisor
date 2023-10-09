package com.appfranchisor.app.ui.franchisee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.api.ApiService
import com.appfranchisor.app.helper.App
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.convertErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class FranchiseeVM  @Inject constructor(private val apiService: ApiService) : ViewModel() {

    fun createOrder(tanggal:String, nama:String, noHp:String,alamat:String,cart:String ) = flow {
        emit(ApiResponse.Loading)
        val response = apiService.createOrder(tanggal, PreferenceHelper.getUserId(App.getContext()!!)!!,
           nama, noHp, alamat,1, cart )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)
    fun pesanProduk( ) = flow {
        emit(ApiResponse.Loading)
        val response = apiService.pesanProduk()
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)


    fun getOrder(idFranchisee:Int) =flow {
        emit(ApiResponse.Loading)
        val response = apiService.getOrder(idFranchisee)
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)

    fun updateStatusOrder(idOrder:Int, status:Int) =flow {
        emit(ApiResponse.Loading)
        val response = apiService.updateStatusOrder(idOrder, status)
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error( "terjadi kesalahan"))

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
            id,   username, password, pemilik, email, alamat, nomorTelpon,  pic, nomorPIC

        )
        if (response.code()==200){
            emit(ApiResponse.Success(response.body()))
        }else emit(ApiResponse.Error(response.convertErrorMessage()!!))

    }.catch {
        emit(ApiResponse.Error( it.message!!))
    }.asLiveData(viewModelScope.coroutineContext)
}