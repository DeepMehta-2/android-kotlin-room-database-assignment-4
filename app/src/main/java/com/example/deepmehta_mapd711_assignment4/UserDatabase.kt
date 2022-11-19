package com.example.deepmehta_mapd711_assignment4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deepmehta_mapd711_assignment4.dao.CustomerDao
import com.example.deepmehta_mapd711_assignment4.dao.OrderDao
import com.example.deepmehta_mapd711_assignment4.dao.ProductDao
import com.example.deepmehta_mapd711_assignment4.entity.Customer
import com.example.deepmehta_mapd711_assignment4.entity.Order
import com.example.deepmehta_mapd711_assignment4.entity.Product

@Database(
    entities = [Customer::class, Product::class, Order::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}