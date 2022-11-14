package com.example.water_drinking_whale.data.main.model

data class TodayRecord(
    val recordDate: String = "",
    val totalSum: Int = 0
)

data class GetTodayRecordResponse(
    val status: Int,
    val data: TodayRecord
)
