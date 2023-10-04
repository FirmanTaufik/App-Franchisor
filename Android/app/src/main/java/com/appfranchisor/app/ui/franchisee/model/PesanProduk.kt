package com.appfranchisor.app.ui.franchisee.model

import com.google.gson.annotations.SerializedName

data class PesanProduk(
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data()
){
    data class Data (

        @SerializedName("produk"   ) var produk   : ArrayList<ProdukModel>   = arrayListOf(),
        @SerializedName("kategori" ) var kategori : ArrayList<Kategori> = arrayListOf()

    )

    data class Kategori (

        @SerializedName("id"         ) var id        : Int?    = null,
        @SerializedName("nama"       ) var nama      : String? = null,
        @SerializedName("gambar"     ) var gambar    : String? = null,
        @SerializedName("created_at" ) var createdAt : String? = null,
        @SerializedName("updated_at" ) var updatedAt : String? = null,
        @SerializedName("isSelected" ) var isSelected : Boolean? = false

    )
}
