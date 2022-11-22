package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.deepmehta_mapd711_assignment4.entity.Customer
import com.example.deepmehta_mapd711_assignment4.entity.Order
import com.example.deepmehta_mapd711_assignment4.entity.Product

class MyViewModel : ViewModel() {

    // calling repository tasks and
    // sending the results to the Activity
    var liveDataCustomer: LiveData<Customer>? = null
    var liveDataProduct: LiveData<List<Product>>? = null
    var liveDataOrder: LiveData<List<Order>>? = null

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

    // insert customer
    fun insertProduct(
        context: Context,
        phoneMaker: String,
        phoneModel: String,
        phoneColor: String,
        storageCapacity: String,
        price: String
    ) {
        MyRepository.insertProduct(
            context,
            phoneMaker,
            phoneModel,
            phoneColor,
            storageCapacity,
            price
        )
    }

    // insert customer
    fun insertOrder(
        context: Context,
        customerId: Int,
        productId: Int,
        orderDate: String,
        status: String
    ) {
        MyRepository.insertOrder(
            context,
            customerId,
            productId,
            orderDate,
            status
        )
    }

    // cancel order
    fun cancelOrder(
        context: Context,
        orderId: Int
    ) {
        MyRepository.cancelOrder(
            context,
            orderId
        )
    }

    fun getCustomer(context: Context): LiveData<Customer>? {
        liveDataCustomer = MyRepository.getCustomer(context)
        return liveDataCustomer
    }

    // Update customer info
    fun updateCustomerInfo(context: Context, custId: Int, password: String, address: String, city: String, postalCode: String) {
        MyRepository.updateCustomerInfo(context, custId, password, address, city, postalCode)
    }

    // Check login credentials
    fun checkLoginCredential(
        context: Context,
        userName: String,
        password: String
    ): LiveData<Customer>? {
        liveDataCustomer = MyRepository.checkLogin(context, userName, password)
        return liveDataCustomer
    }

    // get Product
    fun getProduct(context: Context): LiveData<List<Product>>? {
        liveDataProduct = MyRepository.getProduct(context)
        return liveDataProduct
    }

    // get selected product
    suspend fun getSelectedProduct(context: Context, prodId: Int): Product? {
        return MyRepository.getSelectedProduct(context, prodId!!)
    }

    // get order
    fun getOrder(context: Context): LiveData<List<Order>>? {
        liveDataOrder = MyRepository.getOrder(context)
        return liveDataOrder
    }
}