package com.example.water_drinking_whale.data.main.repository

import com.example.water_drinking_whale.data.main.api.LogApi
import com.example.water_drinking_whale.data.main.model.MonthRecordResponse
import javax.inject.Inject
import javax.inject.Singleton

class LogRepository {

    @Singleton
    class RecordRepository @Inject constructor(
        private val logApi: LogApi
    ) {

        suspend fun getMonthRecord(): MonthRecordResponse = logApi.getMonthRecord()
    }
}
