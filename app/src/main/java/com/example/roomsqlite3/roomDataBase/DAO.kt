package com.example.roomsqlite3.roomDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {
    @Query("SELECT * FROM Data")
    suspend fun getAll() : List<Data>

    @Insert
    suspend fun insert(data: Data)

    @Delete
    suspend fun delete(data: Data)
}