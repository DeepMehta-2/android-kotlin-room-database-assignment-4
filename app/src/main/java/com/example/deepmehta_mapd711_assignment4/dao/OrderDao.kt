package com.example.deepmehta_mapd711_assignment4.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.deepmehta_mapd711_assignment4.entity.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM `Order`")
    fun getAllOrder(): LiveData<List<Order>>

    @Query("UPDATE `Order` SET status = :status WHERE orderId = :orderId")
    fun updateOrder(orderId: Int, status: String)

    @Insert
    fun insert(order: Order)
}