package com.example.roomsqlite3.roomDataBase

interface DatabaseHelper {
    suspend fun getData(): List<Data>

    suspend fun insert(data: Data)

    suspend fun delete(data: Data)

    suspend fun update(data: Data)

}