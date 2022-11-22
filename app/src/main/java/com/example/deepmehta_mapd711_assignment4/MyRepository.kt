package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.deepmehta_mapd711_assignment4.entity.Customer
import com.example.deepmehta_mapd711_assignment4.entity.Order
import com.example.deepmehta_mapd711_assignment4.entity.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MyRepository {
    //defining database and live data object as companion objects
    companion object {
        var userDatabase: UserDatabase? = null
        var customerList: LiveData<Customer>? = null
        var productList: LiveData<List<Product>>? = null
        var orderList: LiveData<List<Order>>? = null

        //initialize database
        fun initializeDB(context: Context): UserDatabase {
            return UserDatabase.getDatabase(context)
        }

        //Initialize insertCustomer()
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
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val customerDetails =
                    Customer(userName, password, firstName, lastName, address, city, postalCode)
                userDatabase!!.customerDao().insert(customerDetails)
            }
        }

        // insert product
        fun insertProduct(
            context: Context,
            phoneMaker: String,
            phoneModel: String,
            phoneColor: String,
            storageCapacity: String,
            price: String
        ) {
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val productDetails =
                    Product(phoneMaker, phoneModel, phoneColor, storageCapacity, price)
                userDatabase!!.productDao().insert(productDetails)
            }
        }

        // insert order
        fun insertOrder(
            context: Context,
            customerId: Int,
            productId: Int,
            orderDate: String,
            status: String
        ) {
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val orderDetails = Order(customerId, productId, orderDate, status)
                userDatabase!!.orderDao().insert(orderDetails)
            }
        }

        // update order
        fun cancelOrder(
            context: Context,
            orderId: Int
        ) {
            userDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                userDatabase!!.orderDao()
                    .updateOrder(orderId, context.getString(R.string.cancelled))
            }
        }

        // getCustomer()
        fun getCustomer(context: Context/*, firstName: String, lastName: String*/): LiveData<Customer>? {
            userDatabase = initializeDB(context)
            customerList = userDatabase!!.customerDao().getAllUser()
            return customerList
        }

        //check login for customer
        fun checkLogin(context: Context, userName: String, password: String): LiveData<Customer>? {
            userDatabase = initializeDB(context)
            return userDatabase!!.customerDao().checkLogin(userName, password)

        }

        // Update customer info
        fun updateCustomerInfo(
            context: Context,
            custId: Int,
            password: String,
            address: String,
            city: String,
            postalCode: String
        ) {
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.customerDao().update(custId, password, address, city, postalCode)
            }
        }

        // get Product
        fun getProduct(context: Context): LiveData<List<Product>>? {
            userDatabase = initializeDB(context)
            productList = userDatabase!!.productDao().getAllProduct()
            return productList
        }

        // get product by ID
        suspend fun getSelectedProduct(context: Context, prodId: Int): Product? {
            userDatabase = initializeDB(context)
            return userDatabase!!.productDao().getProductById(prodId)

        }

        // get orders
        fun getOrder(context: Context): LiveData<List<Order>>? {
            userDatabase = initializeDB(context)
            orderList = userDatabase!!.orderDao().getAllOrder()
            return orderList
        }
    }
}