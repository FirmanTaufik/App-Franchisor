package com.appfranchisor.app.ui.franchisee.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemKategoriBinding

class ItemKategoriAdapter(data: MutableList<Pair<Int, String> > = arrayListOf()) :
    BaseQuickAdapter<Pair<Int, String> , BaseViewHolder>(R.layout.item_kategori, data) {

    override fun convert(holder: BaseViewHolder, item: Pair<Int, String> ) {
        val x = ItemKategoriBinding.bind(holder.itemView)
        x.apply {
            imageView.setImageResource(item.first)
            textViewTitle.text = item.second
        }

        x.root.setOnClickListener {
            setOnItemClick(x.root, getItemPosition(item))
        }
    }


    override fun getItemPosition(item: Pair<Int, String> ?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}