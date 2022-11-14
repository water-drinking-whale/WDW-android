package com.example.water_drinking_whale.data.main.api

import com.example.water_drinking_whale.data.main.model.AddRecordRequest
import com.example.water_drinking_whale.data.main.model.AddRecordResponse
import com.example.water_drinking_whale.data.main.model.GetTodayRecordResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

    @GET("api/v1/record/today")
    suspend fun getTodayRecord(): GetTodayRecordResponse

    @POST("api/v1/record/add")
    suspend fun setTodayRecord(@Body request: AddRecordRequest): AddRecordResponse
}
