package com.example.water_drinking_whale.data.main.model

data class TodayRecord(
    val recordDate: String = "",
    val totalSum: Int = 0
)

data class TodayRecordResponse(
    val status: Int,
    val data: TodayRecord
)

data class NewTodayRecord(
    val username: String,
    val quantity: Int
)

data class NewTodayRecordResponse(
    val status: Int,
    val data: NewTodayRecord
)

data class TodayRecordRequest(
    val quantity: Int
)
