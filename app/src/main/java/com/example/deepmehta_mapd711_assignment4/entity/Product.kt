package com.example.deepmehta_mapd711_assignment4.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Product (
    @ColumnInfo(name = "phoneMaker") val phoneMaker: String,
    @ColumnInfo(name = "phoneModel") val phoneModel: String,
    @ColumnInfo(name = "phoneColor") val phoneColor: String,
    @ColumnInfo(name = "storageCapacity") val storageCapacity: String,
    @ColumnInfo(name = "price") val price: String,
) : Serializable {
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    var productId: Int? = null
}