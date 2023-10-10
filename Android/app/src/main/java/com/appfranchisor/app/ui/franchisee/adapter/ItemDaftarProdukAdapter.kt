package com.appfranchisor.app.ui.franchisee.adapter

import android.content.Intent
import android.view.View
import android.widget.Filter
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.ItemListProdukBinding
import com.appfranchisor.app.databinding.ItemProdukBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.ui.franchisee.model.DaftarProduk
import com.appfranchisor.app.ui.franchisor.ui.FranchisorInputProdukActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.gson.Gson


class ItemDaftarProdukAdapter(data: MutableList<DaftarProduk.Data> = arrayListOf()) :
    BaseQuickAdapter<DaftarProduk.Data, BaseViewHolder>(R.layout.item_list_produk, data) {
      var dataListfull: MutableList<DaftarProduk.Data> ?=null


    override fun convert(holder: BaseViewHolder, item: DaftarProduk.Data ) {
        val x = ItemListProdukBinding.bind(holder.itemView)
        x.apply {
            textViewTitle.text = item.nama
            textViewKategori.text = item.namaKategori
            textViewHarga.text = item.harga?.toDouble()?.convertRupiah() ?: 0.0.convertRupiah()
            Utils.loadImage(context, "${context.resources.getString(R.string.base_url)}/imageproduct/${item.gambar!!}",x.imageView)

        }

        x.root.setOnClickListener {
            val intent = Intent(context, FranchisorInputProdukActivity::class.java)
            intent.putExtra("gson", Gson().toJson(item))
            context.startActivity(intent)
        }

    }

    fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                data = if (charString.isEmpty()) {
                    dataListfull!!
                } else {
                    val filteredList: MutableList<DaftarProduk.Data> = ArrayList()
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
                data = filterResults.values as MutableList<DaftarProduk.Data>
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemPosition(item: DaftarProduk.Data ?): Int {
        return super.getItemPosition(item)
    }

    override fun setOnItemClick(v: View, position: Int) {
        super.setOnItemClick(v, position)
    }
}