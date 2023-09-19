package com.appfranchisor.app.ui.franchisee.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchiseeActivityMainBinding
import com.appfranchisor.app.databinding.FranchiseeActivityMenuBinding
import com.appfranchisor.app.ui.franchisee.adapter.ItemKategoriAdapter
import com.appfranchisor.app.ui.franchisee.adapter.ItemProdukAdapter
import com.appfranchisor.app.ui.franchisee.model.ProdukModel

class FranchiseeMenuActivity : AppCompatActivity() {
    private lateinit var binding:FranchiseeActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOnClick()
        initKetgoriRV()
        initProdukRV()
    }

    private fun initOnClick() {
         binding.buttonCart.setOnClickListener { startActivity(Intent(this, FranchiseeCartActivity::class.java)) }
    }

    private fun initProdukRV() {
        val list = arrayListOf( ProdukModel("https://akcdn.detik.net.id/visual/2015/01/07/415e39c5-1927-42b5-ae6e-6134e4aa074e_169.jpg?w=650","Ayam Karkas","40K") )
        (1..10).forEach { _ -> list.add(ProdukModel("https://akcdn.detik.net.id/visual/2015/01/07/415e39c5-1927-42b5-ae6e-6134e4aa074e_169.jpg?w=650","Ayam Karkas","40K")) }
        val adapter = ItemProdukAdapter(list)
        binding.rvProduk.adapter= adapter
        adapter.notifyDataSetChanged()
    }

    private fun initKetgoriRV() {
        val list = arrayListOf(Pair(R.drawable.produk_1, "Bahan Baku"),Pair(R.drawable.ic_kategori_1, "Bahan Baku"),Pair(R.drawable.produk_1, "Bahan Baku") )
        val adapter = ItemKategoriAdapter(list)
        binding.rvKategori.adapter= adapter
        adapter.notifyDataSetChanged()
    }
}