package com.example.water_drinking_whale.data.database

import com.example.water_drinking_whale.data.database.Notice

interface OnDeleteListener {
    fun onDeleteListener(notice: Notice)
}