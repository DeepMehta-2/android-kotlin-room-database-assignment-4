package com.example.deepmehta_mapd711_assignment4.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.deepmehta_mapd711_assignment4.entity.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE productId LIKE :prodId")
    suspend fun getProductById(prodId: Int): Product?

    @Insert
    fun insert(product: Product)
}