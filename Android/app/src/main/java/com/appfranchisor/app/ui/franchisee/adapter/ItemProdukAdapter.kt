package com.appfranchisor.app.ui.franchisee.adapter

import android.view.View
import android.widget.Filter
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemProdukBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.ui.franchisee.model.ProdukModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class ItemProdukAdapter(data: MutableList<ProdukModel> = arrayListOf()) :
    BaseQuickAdapter<ProdukModel, BaseViewHolder>(R.layout.item_produk, data) {
      var dataListfull: MutableList<ProdukModel> ?=null



    override fun convert(holder: BaseViewHolder, item: ProdukModel ) {
        val x = ItemProdukBinding.bind(holder.itemView)
        x.apply {
            textViewTitle.text = item.nama
            textViewHarga.text = item.harga?.toDouble()?.convertRupiah() ?: 0.0.convertRupiah()
            Utils.loadImage(context, "${context.resources.getString(R.string.base_url)}/imageproduct/${item.gambar!!}",x.image)

        }

        x.root.setOnClickListener {  }
        x.faAdd.setOnClickListener {
            setOnItemClick(x.root, getItemPosition(item))
        }
    }

    fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                data = if (charString.isEmpty()) {
                    dataListfull!!
                } else {
                    val filteredList: MutableList<ProdukModel> = ArrayList()
                    for (row in dataListfull!!) {
                        if (row.nama?.toLowerCase()!!.contains(charString)
                            || row.nama!!.startsWith(charString)
                            || row.nama!!.toUpperCase().contains(charString)
                            || row.nama!!.contains(charString)
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = data
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults
            ) {
                data = filterResults.values as MutableList<ProdukModel>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemPosition(item: ProdukModel ?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}