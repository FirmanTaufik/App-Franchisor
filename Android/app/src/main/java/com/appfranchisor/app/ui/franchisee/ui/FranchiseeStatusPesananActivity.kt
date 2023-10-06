package com.appfranchisor.app.ui.franchisee.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityStatusPesananBinding
import com.appfranchisor.app.databinding.ItemStatusPesananBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.FranchiseeVM
import com.appfranchisor.app.ui.franchisee.model.OrderModel

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
                   /* val list = it.item?.data?.toMutableList()
                    val adapter =ItemStatusPesananAdapter(list!!)
                    binding.rv.adapter=adapter
                    adapter.notifyDataSetChanged()*/
                    addItem( it.item?.data?.toMutableList())

                    binding.progressBar.hide()
                    binding.table.show()
                }
                is ApiResponse.Error ->{
                    it.message.showAsToast()
                    binding.progressBar.hide()
                }
                else ->{
                    binding.table.hide()
                    binding.progressBar.show()
                }
            }
        }
    }

    private fun addItem(toMutableList: MutableList<OrderModel.Data>?) {
        toMutableList?.forEach {item->
            val view = ItemStatusPesananBinding.inflate(LayoutInflater.from(this),  )
            view.apply {
                textViewTracking.text = "#${item.id}"
                textViewNama.text = item.namaPembeli
                textViewDate.text = item.tanggal
                buttonStatus.text = item.status?.convertStatus()
            }
            view.root.setOnClickListener {
                item.id.toString().showAsToast()
            }
            binding.table.addView(view.root)
        }


    }

    fun Int.convertStatus():String{
        return when(this){
            0->"Cancel"
            1->"On Proses"
            else -> "Delivered"
        }
    }
}