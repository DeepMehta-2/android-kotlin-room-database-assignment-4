package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deepmehta_mapd711_assignment4.entity.Customer

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.email_et)
        val password = findViewById<EditText>(R.id.password_et)
        val registerLink = findViewById<TextView>(R.id.registerLink)
        val loginBtn = findViewById<Button>(R.id.login_btn)

        registerLink.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        })

        // Check user login credential on login click
        loginBtn.setOnClickListener(View.OnClickListener {
            val emailStr = email.text.trim()
            val passwordStr = password.text.trim()

            if (emailStr.isEmpty()) {
                email.error = "Email not be empty"
            } else if (passwordStr.isEmpty()) {
                password.error = "Password not be empty"
            } else {
                val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
                myViewModel.checkLoginCredential(this, emailStr.toString(), passwordStr.toString())
                    ?.observe(this,
                        Observer {
                            if (it != null) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)

                                // Store user value on successful login
                                val sharedPreference = getSharedPreferences("My_Pref", Context.MODE_PRIVATE)
                                val editor = sharedPreference.edit()
                                editor.putInt("customerID", it.custId!!)
                                editor.putString("userName", it.userName)
                                editor.putString("password", it.password)
                                editor.putString("address", it.address)
                                editor.putString("postalCode", it.postalCode)
                                editor.putString("city", it.city)
                                editor.putString("firstName", it.firstName)
                                editor.putString("lastName", it.lastName)
                                editor.commit()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Please enter valid user name and password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
            }
        })

    }
}