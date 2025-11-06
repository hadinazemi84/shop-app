package com.example.shopapp.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopapp.config.AppDatabase
import com.example.shopapp.dao.BasketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "online_shop_db"
        ).build()
    }

    @Provides
    fun provideBasketDao(appDatabase: AppDatabase): BasketDao {
        return appDatabase.basketDao()
    }

}