package com.appfranchisor.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.appfranchisor.app.R
import com.appfranchisor.app.api.ApiResponse
import com.appfranchisor.app.databinding.ActivityTransaksiBinding
import com.appfranchisor.app.databinding.DialogFailedBinding
import com.appfranchisor.app.databinding.ItemTransaksiBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils
import com.appfranchisor.app.helper.Utils.addDropdown
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.helper.Utils.hide
import com.appfranchisor.app.helper.Utils.show
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.franchisee.ui.FranchiseeDetailPesananActivity
import com.google.gson.Gson

class TransaksiActivity : MainActivity() {
    private lateinit var binding: ActivityTransaksiBinding
    private val viewModel: MasterVM by viewModels()
    private var idFranchisee :Int? = null
    private var dari : String? = null
    private var hingga : String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        if (PreferenceHelper.getRole(this)==3) {
            idFranchisee = PreferenceHelper.getUserId(this)
        }
        with(binding) {
            buttonDate.setOnClickListener {
                Utils.showDateRangeDialog(supportFragmentManager) {
                    dari = Utils.convertToyyyMMdd(it.first)
                    hingga = Utils.convertToyyyMMdd(it.second)
                    getData()
                    textInfo.show()
                    textInfo.text = "Filter Tanggal $dari - $hingga"
                }
            }
            viewModel.franchisee().observe(this@TransaksiActivity) {
                when (it) {
                    is ApiResponse.Success -> {
                        if (PreferenceHelper.getRole(this@TransaksiActivity)==3) {
                            val nama = it.item!!.data.find { it.id == idFranchisee  }?.pemilik
                            binding.inputFranchisee.editText?.setText(nama)
                            binding.inputFranchisee.editText?.isEnabled=false
                        }else{
                            val list = it.item!!.data.map { it.pemilik }
                            binding.inputFranchisee.addDropdown(list as List<String>) { pos ->
                                idFranchisee = it.item.data[pos].id
                                getData()
                            }
                        }
                        getData()
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun getData(){
        binding.textOmset.text = 0.toDouble().convertRupiah()
        binding.table.removeAllViews()
        val header = ItemTransaksiBinding.inflate(LayoutInflater.from(this@TransaksiActivity),  )
        binding.table.addView(header.root)
        viewModel.transaksi(idFranchisee, dari, hingga)
            .observe(this){
                when (it) {
                    is ApiResponse.Success -> {
                        var total =0
                        val list = it.item?.data
                        list?.forEachIndexed { index, data ->
                            val view = ItemTransaksiBinding.inflate(LayoutInflater.from(this@TransaksiActivity),  )
                            view.apply {
                                total += data.total!!
                                textViewNo.text = index.plus(1).toString()
                                textViewTracking.text = "#${data.id}"
                                textViewFranchisee.text = data.franchisee
                                textViewNama.text = data.namaPembeli
                                textViewTanggal.text = data.tanggal
                                textViewStatus.text = data.status?.convertStatus()
                                textViewBiaya.text =data.total.toString()
                            }
                            view.root.setOnClickListener {
                                val intent = Intent(this, FranchiseeDetailPesananActivity::class.java)
                                intent.putExtra("cart", Gson().toJson(data))
                                startActivity(intent)
                            }
                            binding.table.addView(view.root)

                        }
                        binding.textOmset.text = total.toDouble().convertRupiah()
                        binding.progressBar.hide()

                    }
                    is ApiResponse.Error -> {
                        binding.progressBar.hide()
                        it.message.showAsToast()
                    } else -> binding.progressBar.show()
                }
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