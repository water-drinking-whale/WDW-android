package com.example.water_drinking_whale.data.user.repository

import com.example.water_drinking_whale.data.user.api.UserApi
import com.example.water_drinking_whale.data.user.model.LoginRequest
import com.example.water_drinking_whale.data.user.model.SignUpRequest
import com.example.water_drinking_whale.data.user.model.SignUpResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userApi: UserApi
) {
    suspend fun login(request: LoginRequest): Response<Unit> = userApi.login(request)

    suspend fun signUp(request: SignUpRequest): Response<SignUpResponse> = userApi.signUp(request)
}
