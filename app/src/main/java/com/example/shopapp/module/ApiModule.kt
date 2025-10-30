package com.example.shopapp.module

import com.example.shopapp.api.customer.UserApi
import com.example.shopapp.api.invoices.InvoiceApi
import com.example.shopapp.api.invoices.TransactionApi
import com.example.shopapp.api.products.ProductApi
import com.example.shopapp.api.products.ProductCategoryApi
import com.example.shopapp.api.site.SliderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }


    @Provides
    @Singleton
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }


}