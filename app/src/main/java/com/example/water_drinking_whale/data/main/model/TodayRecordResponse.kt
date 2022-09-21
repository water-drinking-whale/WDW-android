package com.example.water_drinking_whale.data.main.model

import java.util.Date

data class TodayRecordResponse(
    val status: Int,
    val data: TodayRecord
)

data class TodayRecord(
    val recordDate: Date,
    val totalSum: Int
)
