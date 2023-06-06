package com.example.roomsqlite3.roomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data (
    @PrimaryKey val id: Int,
    val title: String,
    val message: String,
    val color: Int
)