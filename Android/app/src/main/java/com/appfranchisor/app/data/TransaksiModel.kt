package com.appfranchisor.app.data

import com.google.gson.annotations.SerializedName

data class TransaksiModel(

    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){
    data class Data (

        @SerializedName("id"            ) var id           : Int?            = null,
        @SerializedName("tanggal"       ) var tanggal      : String?         = null,
        @SerializedName("id_franchisee" ) var idFranchisee : Int?            = null,
        @SerializedName("nama_pembeli"  ) var namaPembeli  : String?         = null,
        @SerializedName("no_hp"         ) var noHp         : Int?            = null,
        @SerializedName("alamat"        ) var alamat       : String?         = null,
        @SerializedName("status"        ) var status       : Int?            = null,
        @SerializedName("created_at"    ) var createdAt    : String?         = null,
        @SerializedName("updated_at"    ) var updatedAt    : String?         = null,
        @SerializedName("franchisee"    ) var franchisee   : String?         = null,
        @SerializedName("total"        ) var total       : Int?            = null,
        @SerializedName("cart"          ) var cart         : ArrayList<Cart> = arrayListOf()

    )
    data class Cart (

        @SerializedName("id"         ) var id        : Int?    = null,
        @SerializedName("id_order"   ) var idOrder   : Int?    = null,
        @SerializedName("id_produk"  ) var idProduk  : Int?    = null,
        @SerializedName("qty"        ) var qty       : Int?    = null,
        @SerializedName("harga"      ) var harga     : Int?    = null,
        @SerializedName("created_at" ) var createdAt : String? = null,
        @SerializedName("updated_at" ) var updatedAt : String? = null,
        @SerializedName("nama"       ) var nama      : String? = null,
        @SerializedName("gambar"     ) var gambar    : String? = null

    )
}
