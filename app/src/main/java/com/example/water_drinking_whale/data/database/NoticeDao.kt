package com.example.water_drinking_whale.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notice: Notice)

    @Query("SELECT * FROM NOTICE")
    fun getAll(): List<Notice>

    @Delete
    fun delete(notice: Notice)
}