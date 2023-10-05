package com.appfranchisor.app.ui.franchisee.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityStatusPesananBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.FranchiseeVM
import com.appfranchisor.app.ui.franchisee.adapter.ItemStatusPesananAdapter

class FranchiseeStatusPesananActivity : MainActivity() {
    lateinit var binding: FranchiseeActivityStatusPesananBinding
    private lateinit var viewModel : FranchiseeVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityStatusPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =   ViewModelProvider(this)[FranchiseeVM:: class.java]
        initRV()
    }

    private fun initRV() {
        viewModel.getOrder(PreferenceHelper.getUserId(this)!!)
            .observe(this) {
            when(it) {
                is ApiResponse.Success ->{
                    val list = it.item?.data?.toMutableList()
                    val adapter =ItemStatusPesananAdapter(list!!)
                    binding.rv.adapter=adapter
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