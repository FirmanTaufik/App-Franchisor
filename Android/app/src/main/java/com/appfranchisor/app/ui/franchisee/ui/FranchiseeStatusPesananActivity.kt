package com.appfranchisor.app.ui.franchisee.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.FranchiseeActivityStatusPesananBinding
import com.appfranchisor.app.databinding.ItemStatusPesananBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.convertStatus
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.FranchiseeVM
import com.appfranchisor.app.ui.franchisee.model.OrderModel
import com.google.gson.Gson

class FranchiseeStatusPesananActivity : MainActivity() {
    lateinit var binding: FranchiseeActivityStatusPesananBinding
    private lateinit var viewModel: FranchiseeVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityStatusPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FranchiseeVM::class.java]
        initRV()
    }

    private fun initRV() {
        viewModel.getOrder(PreferenceHelper.getUserId(this)!!)
            .observe(this) {
                when (it) {
                    is ApiResponse.Success -> {
                        /* val list = it.item?.data?.toMutableList()
                         val adapter =ItemStatusPesananAdapter(list!!)
                         binding.rv.adapter=adapter
                         adapter.notifyDataSetChanged()*/
                        addItem(it.item?.data?.toMutableList())

                        binding.progressBar.hide()
                        binding.table.show()
                    }

                    is ApiResponse.Error -> {
                        it.message.showAsToast()
                        binding.progressBar.hide()
                    }

                    else -> {
                        binding.table.hide()
                        binding.progressBar.show()
                    }
                }
            }
    }

    private fun addItem(toMutableList: MutableList<OrderModel.Data>?) {
        binding.table.removeAllViews()
        toMutableList?.forEach { item ->
            val view = ItemStatusPesananBinding.inflate(LayoutInflater.from(this))
            view.apply {
                textViewTracking.text = "#${item.id}"
                textViewNama.text = item.namaPembeli
                textViewDate.text = item.tanggal
                buttonStatus.text = item.status?.convertStatus()
                when(item.status) {
                    0-> {
                        buttonStatus.strokeColor = ColorStateList.valueOf(Color.RED)
                        buttonStatus.strokeWidth = 1
                        buttonStatus.setTextColor( ColorStateList.valueOf(Color.RED))
                    }
                    1-> {
                        buttonStatus.strokeColor = ColorStateList.valueOf(Color.BLACK)
                        buttonStatus.strokeWidth = 1
                        buttonStatus.setTextColor( ColorStateList.valueOf(Color.BLACK))
                    }
                }
                buttonStatus.setOnClickListener {
                    showPopUp(item, it)
                }
            }
            view.root.setOnClickListener {
                val intent = Intent(this, FranchiseeDetailPesananActivity::class.java)
                intent.putExtra("cart", Gson().toJson(item))
                startActivity(intent)
            }
            binding.table.addView(view.root)
        }


    }

    private fun showPopUp(data: OrderModel.Data, view: View) {
        val popupMenu = PopupMenu(this, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.status_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.starus_1 -> {
                    updateStatus(data.id!!, 1)
                }

                R.id.starus_2 -> {
                    updateStatus(data.id!!, 2)

                }

                R.id.starus_0 -> {
                    updateStatus(data.id!!, 0)
                }
            }

            true
        })

        popupMenu.show()
    }

    private fun updateStatus(idOrder:Int, status:Int){
        viewModel.updateStatusOrder(idOrder, status)
            .observe(this) {
                when (it) {
                    is ApiResponse.Success -> {
                        initRV()
                    }

                    is ApiResponse.Error -> {
                        it.message.showAsToast()
                        binding.progressBar.hide()
                    }

                    else -> {
                        binding.table.hide()
                        binding.progressBar.show()
                    }
                }
            }
    }

}