package com.appfranchisor.app.ui.franchisee.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemCartBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.ui.franchisee.model.CartModel

class ItemCartAdapter(data: MutableList<CartModel> = arrayListOf()) :
    BaseQuickAdapter<CartModel, BaseViewHolder>(R.layout.item_cart, data) {

    private lateinit var listener    : Listener
    fun setListener (listener: Listener){
        this.listener = listener
    }
    override fun convert(holder: BaseViewHolder, item: CartModel ) {
        val x = ItemCartBinding.bind(holder.itemView)
        x.apply {
            textViewTitle.text = item.title
            textViewHarga.text = item.harga.toDouble().convertRupiah()
            textViewQty.text = item.qty.toString()
            Utils.loadImage(context, item.gambar, x.image)
            buttonDelete.setOnClickListener { listener.onDelete(getItemPosition(item)) }
            buttonMin.setOnClickListener { listener.onMin(getItemPosition(item)) }
            buttonPlus.setOnClickListener { listener.onPlus(getItemPosition(item)) }
        }




    }


    override fun getItemPosition(item: CartModel ?): Int {
        return super.getItemPosition(item)
    }

    interface Listener{
        fun onPlus(itemPosition: Int)
        fun onMin(itemPosition: Int)
        fun onDelete(itemPosition: Int)
    }


}