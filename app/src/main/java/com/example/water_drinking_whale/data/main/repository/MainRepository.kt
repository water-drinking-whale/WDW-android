package com.example.water_drinking_whale.data.main.repository

import com.example.water_drinking_whale.data.main.api.MainApi
import com.example.water_drinking_whale.data.main.model.AddRecordRequest
import com.example.water_drinking_whale.data.main.model.AddRecordResponse
import com.example.water_drinking_whale.data.main.model.GetTodayRecordResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val mainApi: MainApi
) {

    suspend fun getTodayRecord(): GetTodayRecordResponse = mainApi.getTodayRecord()

    suspend fun setTodayRecord(request: AddRecordRequest): AddRecordResponse = mainApi.setTodayRecord(request)
}
