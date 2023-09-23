package com.appfranchisor.app.ui.login

import com.google.gson.annotations.SerializedName


data class LoginModel (
    @SerializedName("message"      ) var message     : String? = null,
    @SerializedName("role"      ) var role     : Int? = null,
    @SerializedName("access_token" ) var accessToken : String? = null,
    @SerializedName("token_type"   ) var tokenType   : String? = null

)
