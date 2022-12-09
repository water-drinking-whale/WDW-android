package com.example.water_drinking_whale.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_drinking_whale.data.user.model.SignUpRequest
import com.example.water_drinking_whale.data.user.repository.UserRepository
import com.example.water_drinking_whale.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState

    fun signUp(username: String, password: String, name: String) {
        _networkState.value = NetworkState.LOADING
        viewModelScope.launch {
            val response = userRepository.signUp(SignUpRequest(username = username, password = password, name = name))
            if (response.isSuccessful) {
                _networkState.value = NetworkState.SUCCESS
            } else {
                _networkState.value = NetworkState.FAILURE
            }
        }
    }
}
