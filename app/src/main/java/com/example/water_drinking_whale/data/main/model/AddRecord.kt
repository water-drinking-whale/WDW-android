package com.example.water_drinking_whale.data.main.model

data class AddRecord(
    val username: String,
    val quantity: Int
)

data class AddRecordResponse(
    val status: Int,
    val data: AddRecord
)

data class AddRecordRequest(
    val quantity: Int
)
