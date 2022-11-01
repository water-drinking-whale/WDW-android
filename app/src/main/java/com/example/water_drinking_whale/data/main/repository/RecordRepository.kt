package com.example.water_drinking_whale.data.main.repository

import com.example.water_drinking_whale.data.main.api.RecordApi
import com.example.water_drinking_whale.data.main.model.MonthRecordResponse
import javax.inject.Inject
import javax.inject.Singleton

class RecordRepository {

    @Singleton
    class RecordRepository @Inject constructor(
        private val recordApi: RecordApi
    ) {

        suspend fun getMonthRecord(): MonthRecordResponse = recordApi.getMonthRecord()
    }
}
