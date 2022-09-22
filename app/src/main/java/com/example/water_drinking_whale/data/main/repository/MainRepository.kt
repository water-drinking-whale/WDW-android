package com.example.water_drinking_whale.data.main.repository

import com.example.water_drinking_whale.data.main.api.MainApi
import com.example.water_drinking_whale.data.main.model.TodayRecordResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val mainApi: MainApi
) {

    suspend fun getTodayRecord(): TodayRecordResponse = mainApi.getTodayRecord()
}
