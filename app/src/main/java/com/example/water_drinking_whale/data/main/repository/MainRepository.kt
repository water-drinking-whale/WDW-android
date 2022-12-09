package com.example.water_drinking_whale.data.main.repository

import com.example.water_drinking_whale.data.main.api.MainApi
import com.example.water_drinking_whale.data.main.model.NewTodayRecordResponse
import com.example.water_drinking_whale.data.main.model.TodayRecordRequest
import com.example.water_drinking_whale.data.main.model.TodayRecordResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val mainApi: MainApi
) {

    suspend fun getTodayRecord(): Response<TodayRecordResponse> = mainApi.getTodayRecord()

    suspend fun setTodayRecord(request: TodayRecordRequest): Response<NewTodayRecordResponse> = mainApi.setTodayRecord(request)
}
