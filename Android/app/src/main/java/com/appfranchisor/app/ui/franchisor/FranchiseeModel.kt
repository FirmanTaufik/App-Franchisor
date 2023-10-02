package com.appfranchisor.app.ui.franchisor

import com.google.gson.annotations.SerializedName

data class FranchiseeModel  (
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){
    data class Data (

        @SerializedName("id"                  ) var id                : Int?    = null,
        @SerializedName("username"             ) var username           : String? = null,
        @SerializedName("password"             ) var password           : String? = null,
        @SerializedName("pemilik"             ) var pemilik           : String? = null,
        @SerializedName("email"               ) var email             : String? = null,
        @SerializedName("alamat"              ) var alamat            : String? = null,
        @SerializedName("nomor_telpon_outlet" ) var nomorTelponOutlet : Int?    = null,
        @SerializedName("pic"                 ) var pic               : String? = null,
        @SerializedName("nomor_pic"           ) var nomorPic          : String? = null,
        @SerializedName("created_at"          ) var createdAt         : String? = null,
        @SerializedName("updated_at"          ) var updatedAt         : String? = null

    )
}