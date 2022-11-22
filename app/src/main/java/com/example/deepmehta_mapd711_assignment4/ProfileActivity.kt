package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val email = findViewById<EditText>(R.id.email_et)
        val password = findViewById<EditText>(R.id.password_et)
        val firstName = findViewById<EditText>(R.id.firstName_et)
        val lastName = findViewById<EditText>(R.id.lastName_et)
        val address = findViewById<EditText>(R.id.address_et)
        val city = findViewById<EditText>(R.id.city_et)
        val postalCode = findViewById<EditText>(R.id.postalCode_et)
        val updateBtn = findViewById<Button>(R.id.update_btn)

        val sharedPreference = getSharedPreferences("My_Pref", Context.MODE_PRIVATE)
        val custId = sharedPreference.getInt("customerID", 0)

        email.setText(sharedPreference.getString("userName", ""))
        password.setText(sharedPreference.getString("password", ""))
        firstName.setText(sharedPreference.getString("firstName", ""))
        lastName.setText(sharedPreference.getString("lastName", ""))
        address.setText(sharedPreference.getString("address", ""))
        city.setText(sharedPreference.getString("city", ""))
        postalCode.setText(sharedPreference.getString("postalCode", ""))

        updateBtn.setOnClickListener(View.OnClickListener {
            val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
            myViewModel.updateCustomerInfo(
                this,
                custId,
                password.text.toString(),
                address.text.toString(),
                city.text.toString(),
                postalCode.text.toString()
            )

            val editor = sharedPreference.edit()
            editor.putString("password", password.text.toString())
            editor.putString("address", address.text.toString())
            editor.putString("postalCode", postalCode.text.toString())
            editor.putString("city", city.text.toString())
            editor.commit()

            Toast.makeText(
                this,
                "User Info successfully updated.",
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}