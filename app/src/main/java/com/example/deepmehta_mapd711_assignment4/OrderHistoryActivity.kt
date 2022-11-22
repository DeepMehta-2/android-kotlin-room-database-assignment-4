package com.example.deepmehta_mapd711_assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class OrderHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val mListView = findViewById<ListView>(R.id.orderList)

        myViewModel.getOrder(this)!!.observe(this, Observer {
            if (it.isNotEmpty()) {
                val adapter = OrderListAdapter(this, it, myViewModel)
                mListView.adapter = adapter
            }
        })
    }
}