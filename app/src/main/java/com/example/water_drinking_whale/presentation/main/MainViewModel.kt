package com.example.water_drinking_whale.presentation.main

import androidx.lifecycle.ViewModel
import com.example.water_drinking_whale.data.main.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel()
