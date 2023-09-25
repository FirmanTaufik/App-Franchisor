package com.appfranchisor.app.ui

import com.google.gson.annotations.SerializedName


data class ResponseModel (
    @SerializedName("message"      ) var message     : String? = null,

)
