package com.example.deepmehta_mapd711_assignment4.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.deepmehta_mapd711_assignment4.entity.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customer")
    fun getAllUser(): LiveData<Customer>

    @Query("SELECT * FROM customer WHERE userName LIKE :userName AND password LIKE :password")
    fun checkLogin(userName: String, password: String): LiveData<Customer>?

    @Insert
    fun insert(customer: Customer)

    @Query("UPDATE `Customer` SET password = :password, address=:address, city =:city, postalCode =:postalCode  WHERE custId = :custId")
    fun update(custId: Int, password: String, address: String, city: String, postalCode: String)

    @Delete
    fun delete(customer: Customer)

}