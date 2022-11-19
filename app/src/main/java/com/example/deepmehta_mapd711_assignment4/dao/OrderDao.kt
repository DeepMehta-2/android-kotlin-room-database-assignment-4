package com.example.deepmehta_mapd711_assignment4.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.deepmehta_mapd711_assignment4.entity.Order

@Dao
interface OrderDao {

    @Insert
    fun insert(order: Order)
}