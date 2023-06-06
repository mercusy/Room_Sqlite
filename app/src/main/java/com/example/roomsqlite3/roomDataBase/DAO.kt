package com.example.roomsqlite3.roomDataBase

import androidx.room.*

@Dao
interface DAO {
    @Query("SELECT * FROM Data")
    suspend fun getAll() : List<Data>
    @Insert
    suspend fun insert(data: Data)
    @Delete
    suspend fun delete(data: Data)
    @Update
    suspend fun update(data: Data)

}