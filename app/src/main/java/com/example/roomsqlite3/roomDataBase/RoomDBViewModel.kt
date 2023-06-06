package com.example.roomsqlite3.roomDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed interface LiveData{
    class AllData(val dataFromDb : List<Data>) : LiveData
    object Nothing : LiveData
}

class RoomDBViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = DatabaseHelperImpl(DataBaseBuilder.getInstance(getApplication()))

    val liveData = MutableLiveData<LiveData>(LiveData.Nothing)

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val usersFromDb = dbHelper.getData()
                liveData.value = LiveData.AllData(usersFromDb)

            } catch (_: Exception) {}
        }
    }
    fun addUser(user: Data){
        liveData.value = LiveData.Nothing
        viewModelScope.launch {
            dbHelper.insert(user)
            fetchUsers()

        }
    }
    fun delete(data: Data){
        liveData.value = LiveData.Nothing
        viewModelScope.launch {
            dbHelper.delete(data)
            fetchUsers()
        }
    }
}
