package com.example.shopapp.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.dao.BasketDao
import com.example.shopapp.model.db.BasketEntity

@Database([BasketEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao
}