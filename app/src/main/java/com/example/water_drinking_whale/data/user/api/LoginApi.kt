package com.example.water_drinking_whale.data.user.api

import com.example.water_drinking_whale.data.user.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<Unit>
}
