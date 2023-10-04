package com.appfranchisor.app.ui.franchisee.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemKategoriBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.ui.franchisee.model.PesanProduk

class ItemKategoriAdapter(data: MutableList<PesanProduk.Kategori> = arrayListOf()) :
    BaseQuickAdapter<PesanProduk.Kategori , BaseViewHolder>(R.layout.item_kategori, data) {

    override fun convert(holder: BaseViewHolder, item: PesanProduk.Kategori ) {
        val x = ItemKategoriBinding.bind(holder.itemView)
        x.apply {
            Utils.loadImage(context, "${context.resources.getString(R.string.base_url)}/imagekategori/${item.gambar!!}",imageView)
            textViewTitle.text = item.nama

            if (item.isSelected==true)
                cardView.setCardBackgroundColor(context.resources.getColor(R.color.colorPrimary))
            else cardView.setCardBackgroundColor(context.resources.getColor(R.color.colorBgCard))

        }

        x.root.setOnClickListener {
            setOnItemClick(x.root, getItemPosition(item))
        }
    }

    fun setPositionSelect(position: Int){
        data.forEachIndexed { index, kategori ->
            kategori.isSelected = position==index
        }
        notifyDataSetChanged()
    }

    override fun getItemPosition(item: PesanProduk.Kategori?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}