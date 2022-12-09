package com.example.water_drinking_whale.data.user.model

data class SignUpRequest(
    val username: String,
    val password: String,
    val name: String
)

data class SignUpInfo(
    val id: Long,
    val username: String,
    val name: String
)

data class SignUpResponse(
    val status: Int,
    val data: SignUpInfo
)
