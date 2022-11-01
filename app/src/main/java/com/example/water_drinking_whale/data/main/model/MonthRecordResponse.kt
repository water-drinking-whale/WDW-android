package com.example.water_drinking_whale.data.main.model

import java.util.Date

data class MonthRecordResponse(
    val status: Int,
    val data: MonthRecords
)

data class MonthRecords(
    val records: List<MonthRecord>
)

data class MonthRecord(
    val recordTime: Date,
    val totalSum: Int
)
