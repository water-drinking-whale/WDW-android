package com.example.water_drinking_whale.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WATER_INTAKE")
data class WaterIntake(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var date: String,
    var totalIntake: Int,
    var todayIntake: Int
)
