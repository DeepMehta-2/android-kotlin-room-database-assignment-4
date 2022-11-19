package com.example.deepmehta_mapd711_assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deepmehta_mapd711_assignment4.entity.Customer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtName = findViewById<TextView>(R.id.txtName)

        val userViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        userViewModel.insertCustomer(this, "", "", "Deep", "Mehta", "", "", "")

        var customer : Customer? = null
        userViewModel.getCustomer(this)!!.observe(this, Observer {
            txtName.text = it?.firstName + " " + it?.lastName

            customer = it
        })

        txtName.setOnClickListener(View.OnClickListener {
            if (customer != null)
                userViewModel.deleteCustomer(this, customer!!)
        })
    }
}