package com.appfranchisor.app.ui.franchisee.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemKategoriBinding
import com.appfranchisor.app.databinding.ItemProdukBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.ui.franchisee.model.ProdukModel

class ItemProdukAdapter(data: MutableList<ProdukModel> = arrayListOf()) :
    BaseQuickAdapter<ProdukModel, BaseViewHolder>(R.layout.item_produk, data) {

    override fun convert(holder: BaseViewHolder, item: ProdukModel ) {
        val x = ItemProdukBinding.bind(holder.itemView)
        x.apply {
            textViewTitle.text = item.title
            textViewHarga.text = item.harga
            Utils.loadImage(context, item.gambar, x.image)
        }

        x.root.setOnClickListener {
            setOnItemClick(x.root, getItemPosition(item))
        }
    }


    override fun getItemPosition(item: ProdukModel ?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}