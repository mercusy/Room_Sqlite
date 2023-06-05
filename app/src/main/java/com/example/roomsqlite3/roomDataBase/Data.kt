package com.example.roomsqlite3.roomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data (
    @PrimaryKey val id: Int,
    val name: String,
    val lastname: String,
)