package com.appfranchisor.app.api

sealed class ApiResponse<T>  {
    object Loading : ApiResponse<Nothing>()
    data class Error(val message: String) : ApiResponse<Nothing>()
    data class Success<T>(val item: T) : ApiResponse<T>()
}