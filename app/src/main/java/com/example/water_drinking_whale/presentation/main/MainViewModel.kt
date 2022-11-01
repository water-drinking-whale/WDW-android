package com.example.water_drinking_whale.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_drinking_whale.data.main.model.TodayRecord
import com.example.water_drinking_whale.data.main.model.TodayRecordRequest
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
            mainRepository.setTodayRecord(TodayRecordRequest(quantity = quantity))
            _todayRecord.emit(_todayRecord.value.copy(totalSum = _todayRecord.value.totalSum + quantity))
        }
    }
}
