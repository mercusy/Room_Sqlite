package com.example.roomsqlite3

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsqlite3.roomDataBase.Data
import com.example.roomsqlite3.roomDataBase.LiveData
import com.example.roomsqlite3.roomDataBase.RoomDBViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel : RoomDBViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycle: RecyclerView = findViewById(R.id.recycler)
        recycle.layoutManager = LinearLayoutManager(this)
        val name: EditText = findViewById(R.id.name)
        val surname: EditText = findViewById(R.id.surname)
        val add: Button = findViewById(R.id.add)
        val pref: SharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        var count = 0
        if (pref.contains("count")){
            count = pref.getInt("count",0)
        }else{
            pref.edit().putInt("count",1).apply()
        }

        var users: List<Data>

        viewModel.fetchUsers()

        viewModel.liveData.observe(this){
            when(it){
                is LiveData.AllData ->{
                    users = it.dataFromDb
                    recycle.adapter = CardAdapter(users,::delete)
                }
                is LiveData.Nothing ->{}
                else -> {}
            }
        }
        add.setOnClickListener {
            if (name.text.isNotEmpty() && surname.text.isNotEmpty()){
                viewModel.addUser(Data(count,name.text.toString(), surname.text.toString()))
                count++
                pref.edit().putInt("count",count).apply()
                name.text.clear()
                surname.text.clear()
            }else{
                Toast.makeText(this, "name and surname can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun delete(data: Data){
        viewModel.delete(data)
    }
}