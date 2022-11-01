package com.example.water_drinking_whale.data.main.model

import java.util.Date

data class TodayRecordResponse(
    val status: Int,
    val data: TodayRecord
)

data class TodayRecord(
    val recordDate: Date? = null,
    val totalSum: Int = 0
)

data class NewTodayRecordResponse(
    val status: Int
)

data class TodayRecordRequest(
    val quantity: Int
)
