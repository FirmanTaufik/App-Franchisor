package com.appfranchisor.app.ui.franchisee.model

import com.google.gson.annotations.SerializedName

data class CartModel(
    @SerializedName("id_produk" ) var idProduk : Int?    = null,
    @SerializedName("nama"      ) var nama     : String? = null,
    @SerializedName("gambar"    ) var gambar   : String? = null,
    @SerializedName("qty"       ) var qty      : Int?    = null,
    @SerializedName("harga"     ) var harga    : Int?    = null)
