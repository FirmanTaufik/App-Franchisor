package com.appfranchisor.app.ui.franchisee.model

import com.google.gson.annotations.SerializedName

data class OrderModel(

    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){

    data class Data (

        @SerializedName("id"            ) var id           : Int?            = null,
        @SerializedName("tanggal"       ) var tanggal      : String?         = null,
        @SerializedName("id_franchisee" ) var idFranchisee : Int?            = null,
        @SerializedName("nama_pembeli"  ) var namaPembeli  : String?         = null,
        @SerializedName("status"        ) var status       : Int?            = null,
        @SerializedName("created_at"    ) var createdAt    : String?         = null,
        @SerializedName("updated_at"    ) var updatedAt    : String?         = null,
        @SerializedName("cart"          ) var cart         : ArrayList<CartModel> = arrayListOf()

    )
}
