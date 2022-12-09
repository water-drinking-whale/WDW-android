package com.example.water_drinking_whale.data.user.repository

import com.example.water_drinking_whale.data.user.api.LoginApi
import com.example.water_drinking_whale.data.user.model.LoginRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val loginApi: LoginApi
) {
    suspend fun login(request: LoginRequest): Response<Unit> = loginApi.login(request)
}
