package com.example.shopapp.api.site

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.site.Slider
import retrofit2.http.GET

interface SliderApi {
    @GET("slider")
    suspend fun getSliders(): ApiResponse<Slider>
}