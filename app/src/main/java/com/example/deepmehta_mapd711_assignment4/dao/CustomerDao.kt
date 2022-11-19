package com.example.deepmehta_mapd711_assignment4.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.deepmehta_mapd711_assignment4.entity.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer")
    fun getAllUser(): LiveData<Customer>

    @Insert
    fun insert(customer: Customer)

    @Delete
    fun delete(customer: Customer)

}