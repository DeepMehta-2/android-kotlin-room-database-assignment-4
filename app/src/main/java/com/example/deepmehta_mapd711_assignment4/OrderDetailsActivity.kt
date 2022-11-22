package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.deepmehta_mapd711_assignment4.entity.Product
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderDetailsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val productDetails = intent.getSerializableExtra("productDetails") as Product
        val productId = productDetails.productId

        findViewById<TextView>(R.id.phoneName).text = productDetails.phoneModel
        findViewById<TextView>(R.id.phoneColor).text = productDetails.phoneColor
        findViewById<TextView>(R.id.phoneStorage).text = productDetails.storageCapacity
        findViewById<TextView>(R.id.phonePrice).text = productDetails.price

        val sharedPreference = getSharedPreferences("My_Pref", Context.MODE_PRIVATE)
        val custId = sharedPreference.getInt("customerID", 0)
        val customerName = sharedPreference.getString(
            "firstName",
            ""
        ) + " " + sharedPreference.getString("lastName", "")

        findViewById<TextView>(R.id.customerName).text = "Name: " + customerName
        findViewById<TextView>(R.id.customerAddress).text =
            "Delivery Address: " + sharedPreference.getString("address", "")
        findViewById<TextView>(R.id.customerCity).text =
            "City:  " + sharedPreference.getString("city", "")
        findViewById<TextView>(R.id.customerPostalCode).text =
            "Postal code: " + sharedPreference.getString("postalCode", "")


        // order placed on button click
        findViewById<Button>(R.id.orderPlaceBtn).setOnClickListener(View.OnClickListener {

            // get current date and time and convert into the string
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = current.format(formatter)

            val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
            myViewModel.insertOrder(this, custId, productId!!, formatted, "Ordered")

            Toast.makeText(
                this,
                "Order successfully placed",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        })
    }
}