package com.example.roomsqlite3.roomDataBase

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getData(): List<Data> = appDatabase.dataDao().getAll()
    override suspend fun insert(data: Data) = appDatabase.dataDao().insert(data)
    override suspend fun delete(data: Data) {appDatabase.dataDao().delete(data)}
}
