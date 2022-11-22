package com.example.deepmehta_mapd711_assignment4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deepmehta_mapd711_assignment4.entity.Customer
import com.example.deepmehta_mapd711_assignment4.entity.Product

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val mListView = findViewById<ListView>(R.id.productList)

        // list view item click lister
        mListView.setOnItemClickListener { parent, view, position, l ->
            val selectedItem = parent.getItemAtPosition(position) as Product
            val intent = Intent(this, OrderDetailsActivity::class.java)
            intent.putExtra("productDetails", selectedItem)
            startActivity(intent)
        }

        myViewModel.getProduct(this)!!.observe(this, Observer {
            if (it.isNotEmpty()) {
                // set list adapter
                val adapter: productListAdapter = productListAdapter(this, it)
                mListView.adapter = adapter
            } else {
                // Insert product data for the first time.
                myViewModel.insertProduct(
                    this,
                    "Apple",
                    "Iphone 14 Pro Max",
                    "Space Black",
                    "256 GB",
                    "$ 1599"
                )
                myViewModel.insertProduct(
                    this,
                    "Apple",
                    "Iphone 14 Pro",
                    "Space Black",
                    "256 GB",
                    "$ 1399"
                )
                myViewModel.insertProduct(
                    this,
                    "Apple",
                    "Iphone 14",
                    "Space Black",
                    "256 GB",
                    "$ 1199"
                )
                myViewModel.insertProduct(
                    this,
                    "Samsung",
                    "Galaxy S22+",
                    "Purple",
                    "500 GB",
                    "$ 1399"
                )

                myViewModel.insertProduct(
                    this,
                    "Samsung",
                    "Galaxy S22 Ultra",
                    "Galaxy Black",
                    "256 GB",
                    "$ 1499"
                )

                myViewModel.insertProduct(
                    this,
                    "Google",
                    "Pixel 7",
                    "White",
                    "126 GB",
                    "$ 999"
                )

                myViewModel.insertProduct(
                    this,
                    "Google",
                    "Google Pixel 7 Pro",
                    "White",
                    "256 GB",
                    "$ 1500"
                )
            }
        })
    }

    // Create option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Option menu item click listener
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                // on click open the profile activity
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.order_history -> {
                // on click open the order history activity
                val intent = Intent(this, OrderHistoryActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}