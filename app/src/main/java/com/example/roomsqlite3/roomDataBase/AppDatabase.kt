package com.example.roomsqlite3.roomDataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun dataDao() : DAO
}