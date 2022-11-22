package com.example.deepmehta_mapd711_assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val email = findViewById<EditText>(R.id.email_et)
        val password = findViewById<EditText>(R.id.password_et)
        val firstName = findViewById<EditText>(R.id.firstName_et)
        val lastName = findViewById<EditText>(R.id.lastName_et)
        val address = findViewById<EditText>(R.id.address_et)
        val city = findViewById<EditText>(R.id.city_et)
        val postalCode = findViewById<EditText>(R.id.postalCode_et)
        val signInBtn = findViewById<Button>(R.id.signIn_btn)

        signInBtn.setOnClickListener(View.OnClickListener {
            val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
            myViewModel.insertCustomer(
                this,
                email.text.toString(),
                password.text.toString(),
                firstName.text.toString(),
                lastName.text.toString(),
                address.text.toString(),
                city.text.toString(),
                postalCode.text.toString()
            )

            Toast.makeText(
                this,
                "Successfully registered",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        })
    }
}