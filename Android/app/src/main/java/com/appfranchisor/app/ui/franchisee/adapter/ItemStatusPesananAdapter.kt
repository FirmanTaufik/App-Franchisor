package com.appfranchisor.app.ui.franchisee.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemKategoriBinding
import com.appfranchisor.app.databinding.ItemProdukBinding
import com.appfranchisor.app.databinding.ItemStatusPesananBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.ui.franchisee.model.ProdukModel
import com.appfranchisor.app.ui.franchisee.model.StatusPesananModel

class ItemStatusPesananAdapter(data: MutableList<StatusPesananModel> = arrayListOf()) :
    BaseQuickAdapter<StatusPesananModel, BaseViewHolder>(R.layout.item_status_pesanan, data) {

    override fun convert(holder: BaseViewHolder, item: StatusPesananModel ) {
        val x = ItemStatusPesananBinding.bind(holder.itemView)
        x.apply {
            textViewTracking.text = item.tracking
            textViewNama.text = item.nama
            textViewDate.text = item.tanggal
            buttonStatus.text = item.status
        }

        x.root.setOnClickListener {

        }
    }


    override fun getItemPosition(item: StatusPesananModel ?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}