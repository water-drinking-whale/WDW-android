package com.example.water_drinking_whale.data.main.api

import com.example.water_drinking_whale.data.main.model.NewTodayRecordResponse
import com.example.water_drinking_whale.data.main.model.TodayRecordRequest
import com.example.water_drinking_whale.data.main.model.TodayRecordResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

interface MainApi {

    @GET("api/v1/record/today")
    suspend fun getTodayRecord(): TodayRecordResponse

    @POST("api/v1/record/add")
    suspend fun setTodayRecord(@Part request: TodayRecordRequest): NewTodayRecordResponse
}
