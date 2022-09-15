package com.example.water_drinking_whale.data.main.api

import com.example.water_drinking_whale.data.main.model.TodayRecordResponse
import retrofit2.http.GET

interface MainApi {

    @GET("api/v1/record/today")
    suspend fun getTodayRecord(): TodayRecordResponse

}