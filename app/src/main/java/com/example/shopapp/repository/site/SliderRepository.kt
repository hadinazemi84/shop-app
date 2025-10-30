package com.example.shopapp.repository.site

import com.example.shopapp.api.site.SliderApi
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.site.Slider
import com.example.shopapp.repository.base.BaseRepository
import jakarta.inject.Inject

class SliderRepository @Inject constructor(val api: SliderApi) : BaseRepository() {

    suspend fun getSliders(): ApiResponse<Slider> = safeApiCall { api.getSliders() }

}