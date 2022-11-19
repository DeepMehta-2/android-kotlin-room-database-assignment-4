package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.deepmehta_mapd711_assignment4.entity.Customer

class MyViewModel : ViewModel() {

    // calling repository tasks and
    // sending the results to the Activity
    var liveDataCustomer: LiveData<Customer>? = null

    // insert customer
    fun insertCustomer(
        context: Context,
        userName: String,
        password: String,
        firstName: String,
        lastName: String,
        address: String,
        city: String,
        postalCode: String
    ) {
        MyRepository.insertCustomer(
            context,
            userName,
            password,
            firstName,
            lastName,
            address,
            city,
            postalCode
        )
    }

    fun getCustomer(context: Context/*, firstName: String, lastName: String*/): LiveData<Customer>? {
        liveDataCustomer = MyRepository.getCustomer(context/*, firstName, lastName*/)
        return liveDataCustomer
    }

    fun deleteCustomer(context: Context, custId: Customer) {
        MyRepository.deleteCustomer(context,custId)
    }
}