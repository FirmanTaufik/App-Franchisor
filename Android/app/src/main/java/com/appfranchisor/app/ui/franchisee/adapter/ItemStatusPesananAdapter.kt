package com.appfranchisor.app.ui.franchisee.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemStatusPesananBinding
import com.appfranchisor.app.ui.franchisee.model.OrderModel
import com.appfranchisor.app.ui.franchisee.ui.FranchiseeCartActivity
import com.google.gson.Gson

class ItemStatusPesananAdapter(data: MutableList<OrderModel.Data> = arrayListOf()) :
    BaseQuickAdapter<OrderModel.Data, BaseViewHolder>(R.layout.item_status_pesanan, data) {

    override fun convert(holder: BaseViewHolder, item: OrderModel.Data ) {
        val x = ItemStatusPesananBinding.bind(holder.itemView)
        x.apply {
            textViewTracking.text = "#${item.id}"
            textViewNama.text = item.namaPembeli
            textViewDate.text = item.tanggal
            buttonStatus.text = item.status?.convertStatus()
        }

        x.root.setOnClickListener {
            val intent =Intent(context, FranchiseeCartActivity::class.java)
            intent.putExtra("cart", Gson().toJson(item.cart))
            context.startActivity(intent)
        }
    }

    fun Int.convertStatus():String{
        return when(this){
            0->"Cancel"
            1->"On Proses"
            else -> "Delivered"
        }
    }

    override fun getItemPosition(item: OrderModel.Data ?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}