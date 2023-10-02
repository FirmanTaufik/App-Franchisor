package com.appfranchisor.app.data

import com.google.gson.annotations.SerializedName

data class KategoriModel(

    @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()
) {
    data class Data (

        @SerializedName("id"         ) var id        : Int?    = null,
        @SerializedName("nama"       ) var nama      : String? = null,
        @SerializedName("gambar"     ) var gambar    : String? = null,
        @SerializedName("created_at" ) var createdAt : String? = null,
        @SerializedName("updated_at" ) var updatedAt : String? = null

    )
}
