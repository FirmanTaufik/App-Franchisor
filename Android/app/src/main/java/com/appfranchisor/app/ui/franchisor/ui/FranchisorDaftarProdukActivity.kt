package com.appfranchisor.app.ui.franchisor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchisorActivityDaftarProdukBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.listenOnChange
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.adapter.ItemDaftarProdukAdapter
import com.appfranchisor.app.ui.franchisor.FranchisorVM

class FranchisorDaftarProdukActivity : MainActivity() {
    private lateinit var viewModel : FranchisorVM
    private lateinit var binding : FranchisorActivityDaftarProdukBinding
    private   val adapter = ItemDaftarProdukAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FranchisorActivityDaftarProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[FranchisorVM:: class.java]
        binding.inputSearch.editText.listenOnChange {
            adapter.getFilter()?.filter(it)
        }

    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun getData() {
        viewModel.produk(PreferenceHelper.getUserId(this)!!)
            .observe(this){
                when(it) {
                    is ApiResponse.Success ->{
                        adapter .data =  it.item!!.data
                        adapter.dataListfull = it.item!!.data
                        binding.rv.adapter =adapter
                        adapter.notifyDataSetChanged()
                        binding.progressBar.hide()
                    }
                    is ApiResponse.Error ->{
                        it.message.showAsToast()
                        binding.progressBar.hide()
                    }
                    else ->{
                        binding.progressBar.show()
                    }
                }
            }
    }
}