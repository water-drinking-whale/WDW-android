package com.example.water_drinking_whale.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NOTICE")
data class Notice(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    var hour: Int,
    var minute: Int,
    var am_pm: String?
)
