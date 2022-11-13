package com.example.water_drinking_whale.data.main.api

import com.example.water_drinking_whale.data.main.model.MonthRecordResponse
import retrofit2.http.GET

interface LogApi {

    @GET("api/v1/record/month")
    suspend fun getMonthRecord(): MonthRecordResponse
}
