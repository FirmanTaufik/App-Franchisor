package com.appfranchisor.app.data

import com.google.gson.annotations.SerializedName

data class FilterOrderModel(
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
) {
    data class Data (

        @SerializedName("id"      ) var id      : Int?    = null,
        @SerializedName("nama"    ) var nama    : String? = null,
        @SerializedName("terjual" ) var terjual : String? = null

    )
}
