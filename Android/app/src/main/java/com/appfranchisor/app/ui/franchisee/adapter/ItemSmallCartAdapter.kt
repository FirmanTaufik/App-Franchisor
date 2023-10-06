package com.appfranchisor.app.ui.franchisee.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemSmallCartBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.ui.franchisee.model.CartModel

class ItemSmallCartAdapter(data: MutableList<CartModel> = arrayListOf()) :
    BaseQuickAdapter<CartModel, BaseViewHolder>(R.layout.item_small_cart, data) {


    override fun convert(holder: BaseViewHolder, item: CartModel ) {
        val x = ItemSmallCartBinding.bind(holder.itemView)
        x.apply {
            Utils.loadImage(context, "${context.resources.getString(R.string.base_url)}/imageproduct/${item.gambar!!}",x.imageView)

        }




    }


    override fun getItemPosition(item: CartModel ?): Int {
        return super.getItemPosition(item)
    }




}