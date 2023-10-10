package com.appfranchisor.app.data

import com.google.gson.annotations.SerializedName

data class PendapatanModel(@SerializedName("message" ) var message : String?         = null,
                           @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
){
    data class Data (

        @SerializedName("id"                   ) var id                 : Int?    = null,
        @SerializedName("nama"                 ) var nama               : String? = null,
        @SerializedName("terjual_saat_ini"     ) var terjualSaatIni     : String? = null,
        @SerializedName("terjual_saat_kemarin" ) var terjualSaatKemarin : String? = null

    )
}
