package com.example.deepmehta_mapd711_assignment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @ColumnInfo(name = "customerId") val customerId: Int,
    @ColumnInfo(name = "productId") val productId: Int,
    @ColumnInfo(name = "orderDate") val orderDate: String,
    @ColumnInfo(name = "status") val status: String,
) {
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    var orderId: Int? = null
}
