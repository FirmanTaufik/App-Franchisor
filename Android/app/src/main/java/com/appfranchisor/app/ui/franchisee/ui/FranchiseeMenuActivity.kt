package com.appfranchisor.app.ui.franchisee.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityMenuBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.listenOnChange
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.FranchiseeVM
import com.appfranchisor.app.ui.franchisee.adapter.ItemKategoriAdapter
import com.appfranchisor.app.ui.franchisee.adapter.ItemProdukAdapter
import com.appfranchisor.app.ui.franchisee.model.CartModel
import com.appfranchisor.app.ui.franchisee.model.PesanProduk
import com.appfranchisor.app.ui.franchisee.model.ProdukModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FranchiseeMenuActivity : MainActivity() {
    private lateinit var binding:FranchiseeActivityMenuBinding
    private lateinit var viewModel : FranchiseeVM
    private val adapter = ItemProdukAdapter( )
    private var produk: ArrayList<ProdukModel> = arrayListOf()
    var listCart = arrayListOf<CartModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[FranchiseeVM:: class.java]
        initOnClick()
        getData()
        binding.editTextSearch.listenOnChange(){
            val keyword = binding.editTextSearch.text.toString()
            adapter.getFilter()?.filter(keyword)
        }
    }

    private fun getData() {
        viewModel.pesanProduk().observe(this) {
            when(it) {
                is ApiResponse.Success ->{
                    binding.swipeRefresh.isRefreshing=false
                    binding.progressBar.hide()
                    val response = it.item!!
                    initKetgoriRV(response.data!!.kategori)
                    produk=response.data!!.produk
                    initProdukRV(  1)
                }
                is ApiResponse.Error ->{
                    binding.swipeRefresh.isRefreshing=false
                    it.message.showAsToast()
                    binding.progressBar.hide()
                }
                else ->{
                    binding.progressBar.show()
                }
            }
        }
    }

    private fun initOnClick() {
        binding.swipeRefresh.setOnRefreshListener {
            getData()
        }
         binding.buttonCart.setOnClickListener { startActivity(Intent(this, FranchiseeCartActivity::class.java)) }
    }

    private fun initProdukRV( idKategori: Int) {
        val list =  produk.filter { it.idKategori== idKategori}
        adapter.dataListfull = list.toMutableList()
        adapter.data =list.toMutableList()
        binding.rvProduk.adapter= adapter
        adapter.setOnItemClickListener { _, _, position ->
            addItemToCart(adapter.data[position])
        }
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        initListCart()
        super.onResume()
    }
    private fun addItemToCart(produkModel: ProdukModel) {
        val listId = listCart.map { it.idProduk }
        if (produkModel.id in  listId) {
            println("addItemToCart eee")
            listCart.forEach {
                if (it.idProduk==produkModel.id) {
                    it.qty =   it.qty?.plus(1)
                }
            }
        }else{
            val cart = CartModel()
            cart.idProduk = produkModel.id
            cart.gambar = produkModel.gambar
            cart.harga =produkModel.harga
            cart.qty = 1
            cart.nama = produkModel.nama
            listCart.add(cart)
        }
        val json = Gson().toJson(listCart)
        PreferenceHelper.setCart(this,json )
    }

    private fun initListCart() {
        if (PreferenceHelper.getCart(this)!=null){
            val listType = object : TypeToken<ArrayList<CartModel?>?>() {}.type
            listCart= Gson().fromJson(PreferenceHelper.getCart(this),listType)
        }
    }

    private fun initKetgoriRV(kategori: ArrayList<PesanProduk.Kategori>) {
        kategori.first().isSelected=true
        val adapter = ItemKategoriAdapter(kategori)
        binding.rvKategori.adapter= adapter
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickListener { _, _, position ->
            binding.editTextSearch.setText("")
            adapter.setPositionSelect(position)
            val id = adapter.data[position].id
            if (id != null) {
                initProdukRV(id)
            }
        }
    }
}