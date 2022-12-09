package com.example.water_drinking_whale.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_drinking_whale.WDWApp
import com.example.water_drinking_whale.data.user.model.LoginRequest
import com.example.water_drinking_whale.data.user.repository.LoginRepository
import com.example.water_drinking_whale.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState

    fun login(username: String, password: String) {
        _networkState.value = NetworkState.LOADING
        viewModelScope.launch {
            val response = loginRepository.login(LoginRequest(username = username, password = password))
            if (response.isSuccessful) {
                val token = response.headers()["Authorization"].toString()
                if (token.isNotEmpty()) {
                    WDWApp.sharedPreferencesUtil.setSharedPreferences("token", token)
                    _networkState.value = NetworkState.SUCCESS
                }
            } else {
                _networkState.value = NetworkState.FAILURE
            }
        }
    }
}
