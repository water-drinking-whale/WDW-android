package com.example.water_drinking_whale.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_drinking_whale.data.main.model.AddRecordRequest
import com.example.water_drinking_whale.data.main.model.TodayRecord
import com.example.water_drinking_whale.data.main.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _todayRecord: MutableStateFlow<TodayRecord> = MutableStateFlow(TodayRecord())
    val todayRecord: StateFlow<TodayRecord> = _todayRecord

    init {
        setTodayRecord()
    }

    private fun setTodayRecord() {
        viewModelScope.launch {
            _todayRecord.value = mainRepository.getTodayRecord().data
        }
    }

    fun addWaterIntake(quantity: Int) {
        viewModelScope.launch {
            mainRepository.setTodayRecord(AddRecordRequest(quantity = quantity))
            // 현재처럼 내부 totalSum에 quantity 더한 값을 세팅하는 것이 아닌,
            // 서버에서 setTodayRecord 호출 후 request 값으로 넘어오는 totalSum으로 세팅해야 함
            _todayRecord.emit(_todayRecord.value.copy(totalSum = _todayRecord.value.totalSum + quantity))
        }
    }
}
