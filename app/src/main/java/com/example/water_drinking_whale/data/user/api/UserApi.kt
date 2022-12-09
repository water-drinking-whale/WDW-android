package com.example.water_drinking_whale.data.user.api

import com.example.water_drinking_whale.data.user.model.LoginRequest
import com.example.water_drinking_whale.data.user.model.SignUpRequest
import com.example.water_drinking_whale.data.user.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<Unit>

    @POST("api/v1/join")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>
}
