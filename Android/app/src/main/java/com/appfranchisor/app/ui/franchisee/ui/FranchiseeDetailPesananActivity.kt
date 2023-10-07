package com.appfranchisor.app.ui.franchisee.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchiseeActivityDetailPesananBinding
import com.appfranchisor.app.databinding.ItemPesananDetailBinding
import com.appfranchisor.app.databinding.ItemStatusPesananBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.helper.Utils.convertStatus
import com.appfranchisor.app.ui.franchisee.model.OrderModel
import com.google.gson.Gson

class FranchiseeDetailPesananActivity : AppCompatActivity() {
    private lateinit var binding :FranchiseeActivityDetailPesananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityDetailPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data   =Gson().fromJson(intent.getStringExtra("cart")!!, OrderModel.Data::class.java)
        setUI(data)
    }

    private fun setUI(data: OrderModel.Data?) {
        data!!.apply {
            binding.let {
                it.textViewNama.text = this.namaPembeli
                it.textViewTracking.text = "#"+id.toString()
                it.textViewAlamat.text = alamat
                it.textViewNoHp.text = noHp
                it.textViewStatus.text = status?.convertStatus()
                var total :Double =0.0
                cart.forEach { item->
                    val harga = item.qty!! * item.harga!!
                    total += harga
                    val view = ItemPesananDetailBinding.inflate(LayoutInflater.from(this@FranchiseeDetailPesananActivity),  )
                    view. textViewTitle.text = item.nama
                    view. textViewHarga.text = harga .toString()
                    view.   textViewQty.text = "x"+item.qty.toString()
                    Utils.loadImage(this@FranchiseeDetailPesananActivity, "${ resources.getString(R.string.base_url)}/imageproduct/${item.gambar!!}",  view. image)
                    binding.linearMain.addView(view.root)
                }
                binding.textViewTotalHarga.text = total.convertRupiah()
            }
        }
    }
}