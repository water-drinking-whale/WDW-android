package com.example.water_drinking_whale.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterIntakeDao {
    @Query("SELECT * FROM WATER_INTAKE WHERE date = :date")
    fun getIntake(date: String): WaterIntake

    @Query("SELECT todayIntake FROM WATER_INTAKE WHERE date = :date")
    fun getTodayIntake(date: String): Int

    @Query("SELECT totalIntake FROM WATER_INTAKE WHERE date = :date")
    fun getTotalIntake(date: String): Int

    @Insert
    fun insert(waterIntake: WaterIntake)
}
