package com.appfranchisor.app.ui.franchisee.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appfranchisor.app.R
import com.appfranchisor.app.databinding.FranchiseeActivityInputTransaksiBinding
import com.appfranchisor.app.helper.PreferenceHelper
import com.appfranchisor.app.helper.Utils.convertRupiah
import com.appfranchisor.app.helper.Utils.showAsToast
import com.appfranchisor.app.ui.MainActivity
import com.appfranchisor.app.ui.franchisee.adapter.ItemSmallCartAdapter
import com.appfranchisor.app.ui.franchisee.model.CartModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FranchiseeInputTransaksiActivity : MainActivity() {
    private lateinit var binding:FranchiseeActivityInputTransaksiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FranchiseeActivityInputTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOnClick()
    }

    override fun onResume() {
        initListCart()
        super.onResume()
    }
    private fun initOnClick() {
        binding.apply {
            cardAdd.setOnClickListener { startActivity(Intent(this@FranchiseeInputTransaksiActivity, FranchiseeMenuActivity::class.java))  }
            buttonAddToCart.setOnClickListener {
                if (editTextNama.text.isNullOrEmpty()) {
                    "Nama Masih Kosong".showAsToast()
                    return@setOnClickListener
                }
                if (editTextNoHp.text.isNullOrEmpty()) {
                    "No Hp Masih Kosong".showAsToast()
                    return@setOnClickListener
                }
                if (editTextAlamat.text.isNullOrEmpty()) {
                    "Alamat Masih Kosong".showAsToast()
                    return@setOnClickListener
                }
                val intent = Intent(this@FranchiseeInputTransaksiActivity, FranchiseeCartActivity::class.java)

                val nama = editTextNama.text.toString()
                val noHp = editTextNoHp.text.toString()
                val alamat = editTextAlamat.text.toString()
                intent.putExtra("nama", nama)
                intent.putExtra("noHp", noHp)
                intent.putExtra("alamat", alamat)
                println("daagfgaf $nama $noHp $alamat")
               startActivity(intent)
            }

        }
    }

    private fun initListCart() {
        binding.textViewTotalHarga.text = 0.0.convertRupiah()
        if (PreferenceHelper.getCart(this)!=null){
            val listType = object : TypeToken<ArrayList<CartModel?>?>() {}.type
            val listCart:ArrayList<CartModel> = Gson().fromJson(PreferenceHelper.getCart(this),listType)
            val adapter = ItemSmallCartAdapter(listCart)
            binding.rv.adapter=adapter
            adapter.notifyDataSetChanged()
            GlobalScope.launch(Dispatchers.Main) {
                delay(1000)
                binding.rv.scrollToPosition(listCart.size-1)
            }
            var totalHarga =0.0
            adapter.data.forEach {
                val harga = it.qty!!* it.harga!!
                totalHarga += harga
            }
            binding.textViewTotalHarga.text = totalHarga.convertRupiah()
        }
    }
}