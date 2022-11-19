package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.deepmehta_mapd711_assignment4.entity.Customer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MyRepository {
    //defining database and live data object as companion objects
    companion object {
        var userDatabase: UserDatabase? = null
        var customer: LiveData<Customer>? = null

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

        //Initialize getCustomer()
        fun getCustomer(context: Context/*, firstName: String, lastName: String*/): LiveData<Customer>? {
            userDatabase = initializeDB(context)
            customer = userDatabase!!.customerDao().getAllUser()
            return customer
        }

        fun deleteCustomer(context: Context, custId: Customer) {
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.customerDao().delete(custId)
            }
        }

    }
}