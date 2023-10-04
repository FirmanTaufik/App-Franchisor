package com.appfranchisor.app.ui.franchisee.model

import com.google.gson.annotations.SerializedName

data class ProdukModel(
    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("id_franchisor" ) var idFranchisor : Int?    = null,
    @SerializedName("id_kategori"   ) var idKategori   : Int?    = null,
    @SerializedName("nama"          ) var nama         : String? = null,
    @SerializedName("harga"         ) var harga        : Int?    = null,
    @SerializedName("gambar"        ) var gambar       : String? = null,
    @SerializedName("created_at"    ) var createdAt    : String? = null,
    @SerializedName("updated_at"    ) var updatedAt    : String? = null)
