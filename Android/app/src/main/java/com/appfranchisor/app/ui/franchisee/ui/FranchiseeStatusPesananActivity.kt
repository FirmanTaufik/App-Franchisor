package com.appfranchisor.app.ui.franchisee.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchiseeActivityStatusPesananBinding
import com.appfranchisor.app.ui.franchisee.adapter.ItemStatusPesananAdapter
import com.appfranchisor.app.ui.franchisee.model.StatusPesananModel

class FranchiseeStatusPesananActivity : AppCompatActivity() {
    lateinit var binding: FranchiseeActivityStatusPesananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityStatusPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
    }

    private fun initRV() {
        val list = arrayListOf(StatusPesananModel("#ff1245","Deany", "12/05/21","Deliverd"))
        (1..10).forEach { list.add(StatusPesananModel("#ff1245","Deany", "12/05/21","Deliverd"))  }
        val adapter =ItemStatusPesananAdapter(list)
        binding.rv.adapter=adapter
        adapter.notifyDataSetChanged()
    }
}