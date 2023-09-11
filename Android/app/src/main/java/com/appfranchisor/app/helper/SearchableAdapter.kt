package com.appfranchisor.app.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import java.util.Locale

class SearchableAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val allPois: List<String>):
    ArrayAdapter<String>(context, layoutResource, allPois),
    Filterable {
    private var mPois: List<String> = allPois

    override fun getCount(): Int {
        return mPois.size
    }

    override fun getItem(p0: Int): String {
        return mPois[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        view.text = "${mPois[position]}"
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                mPois = filterResults.values as List<String>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allPois
                else
                    allPois.filter {
                        it.toLowerCase().contains(queryString) ||
                                it.toLowerCase().contains(queryString) ||
                                it.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}