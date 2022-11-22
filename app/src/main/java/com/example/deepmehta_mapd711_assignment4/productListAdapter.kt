package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.deepmehta_mapd711_assignment4.entity.Product

class productListAdapter(
    private val context: Context,
    private val productList: List<Product>
) : BaseAdapter() {


    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return productList.indexOf(getItem(position)).toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.row_design_product, p2, false)

        view.findViewById<TextView>(R.id.phoneName).text = productList[position].phoneModel
        view.findViewById<TextView>(R.id.phoneColor).text = productList[position].phoneColor
        view.findViewById<TextView>(R.id.phoneStorage).text = productList[position].storageCapacity
        view.findViewById<TextView>(R.id.phonePrice).text = productList[position].price

        return view
    }

}