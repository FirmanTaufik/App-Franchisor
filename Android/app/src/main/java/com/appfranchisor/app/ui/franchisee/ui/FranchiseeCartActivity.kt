package com.appfranchisor.app.ui.franchisee.ui

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchiseeActivityCartBinding
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.franchisee.adapter.ItemCartAdapter
import com.appfranchisor.app.ui.franchisee.model.CartModel

class FranchiseeCartActivity : AppCompatActivity() {
    private lateinit var binding :FranchiseeActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
    }

    private fun initRV() {
        val list = arrayListOf(CartModel("https://akcdn.detik.net.id/visual/2015/01/07/415e39c5-1927-42b5-ae6e-6134e4aa074e_169.jpg?w=650",
           "Ayam Mentah", 50000, 1 ) )
        (1..10).forEach { list.add(CartModel("https://akcdn.detik.net.id/visual/2015/01/07/415e39c5-1927-42b5-ae6e-6134e4aa074e_169.jpg?w=650",
            "Ayam Mentah", 50000, 1 ) ) }
        val adapter = ItemCartAdapter(list)
        binding.rv.adapter= adapter
        adapter.setListener(object : ItemCartAdapter.Listener{
            override fun onPlus(itemPosition: Int) {
                "onPlus".showAsToast()

            }

            override fun onMin(itemPosition: Int) {
                "onMin".showAsToast()


            }

            override fun onDelete(itemPosition: Int) {
                "onDelete".showAsToast()


            }
        })
        adapter.notifyDataSetChanged()
    }
}