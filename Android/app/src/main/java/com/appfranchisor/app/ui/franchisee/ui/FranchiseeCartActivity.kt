package com.appfranchisor.app.ui.franchisee.ui

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityCartBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.FranchiseeVM
import com.appfranchisor.app.ui.franchisee.adapter.ItemCartAdapter
import com.appfranchisor.app.ui.franchisee.model.CartModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FranchiseeCartActivity : MainActivity() {
    private lateinit var binding :FranchiseeActivityCartBinding
    val adapter = ItemCartAdapter()
    private lateinit var viewModel : FranchiseeVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[FranchiseeVM:: class.java]
        initRV()
        initOnClick()
    }

    private fun initOnClick() {
        binding.buttonStatusPesanan
            .setOnClickListener {
                if (adapter.data.isEmpty())
                    "Cart Masih Kosong".showAsToast()
                else saveDatabase()
            }
    }

    private fun saveDatabase() {
        val cartJson = Gson().toJson(adapter.data)
        viewModel.createOrder(Utils.dateyyyyMMdd, "tesnama", cartJson).observe(this) {
            when(it) {
                is ApiResponse.Success ->{
                    "Sukses membuat Order".showAsToast()
                    binding.buttonStatusPesanan.show()
                    binding.progressBar.hide()
                   PreferenceHelper.removeChart( this)
                    finish()
                }
                is ApiResponse.Error ->{
                    it.message.showAsToast()
                    binding.buttonStatusPesanan.show()
                    binding.progressBar.hide()
                }
                else ->{
                    binding.buttonStatusPesanan.hide()
                    binding.progressBar.show()
                }
            }
        }
    }

    private fun initRV() {
        var listCart = arrayListOf<CartModel>()
        if (PreferenceHelper.getCart(this)!=null){
            val listType = object : TypeToken<ArrayList<CartModel?>?>() {}.type
            listCart= Gson().fromJson(PreferenceHelper.getCart(this),listType)
        }
        adapter.data =listCart
        binding.rv.adapter= adapter
        adapter.setListener(object : ItemCartAdapter.Listener{
            override fun onPlus(itemPosition: Int) {
                listCart[itemPosition].qty = listCart[itemPosition].qty!! + 1
                adapter.notifyDataSetChanged()
                saveList()
            }

            override fun onMin(itemPosition: Int) {
                val qty = listCart[itemPosition].qty
                if (qty != null) {
                    if (qty>1){
                        listCart[itemPosition].qty = listCart[itemPosition].qty!! - 1
                    }
                }

                adapter.notifyDataSetChanged()
                saveList()
            }

            override fun onDelete(itemPosition: Int) {
                listCart.removeAt(itemPosition)
                adapter.notifyDataSetChanged()
                saveList()
            }
        })
        adapter.notifyDataSetChanged()
        saveList()

    }

    override fun onBackPressed() {
        saveList()
        finish()
        super.onBackPressed()
    }

    private fun saveList() {
        var totalHarga =0.0
        adapter.data.forEach {
            val harga = it.qty!!* it.harga!!
            totalHarga += harga
        }
        binding.textViewTotalHarga.text = totalHarga.convertRupiah()
        binding.textViewCartItemCount.text = "${adapter.data.size} item in Cart"
        val listCart =   adapter.data
        val json = Gson().toJson(listCart)
        PreferenceHelper.setCart(this,json )
    }
}