package com.example.roomsqlite3.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomsqlite3.R
import com.example.roomsqlite3.databinding.FragmentAddBinding
import com.example.roomsqlite3.roomDataBase.Data
import com.example.roomsqlite3.roomDataBase.RoomDBViewModel
import kotlin.math.absoluteValue

class AddFragment : Fragment(R.layout.fragment_add) {
    private val viewModel : RoomDBViewModel by viewModels()
    lateinit var binding: FragmentAddBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        var color = R.color.C4.absoluteValue

        val pref: SharedPreferences = requireActivity().getSharedPreferences("myPref",MODE_PRIVATE)
        var count = 0
        if (pref.contains("count")){
            count = pref.getInt("count",0)
        }else{
            pref.edit().putInt("count",1).apply()
        }

        binding.add.setOnClickListener {
            val title: String = binding.title.text.toString()
            val message: String = binding.message.text.toString()

            if ( title.isNotEmpty() && message.isNotEmpty()){
                viewModel.addUser(Data(count,title,message,color))
                count++
                pref.edit().putInt("count",count).apply()
                findNavController().popBackStack()

            }else{
                Toast.makeText(requireContext(), "title and message can't be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding.C1.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C1)
            color = R.color.C1.absoluteValue
        }
        binding.C2.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C2)
            color = R.color.C2.absoluteValue
        }
        binding.C3.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C3)
            color = R.color.C3.absoluteValue
        }
        binding.C4.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C4)
            color = R.color.C4.absoluteValue
        }
        binding.C5.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C5)
            color = R.color.C5.absoluteValue
        }
        binding.C6.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C6)
            color = R.color.C6.absoluteValue
        }
        binding.C7.setOnClickListener {
            binding.card.setBackgroundResource(R.color.C7)
            color = R.color.C7.absoluteValue
        }
        binding.delAll.setOnClickListener {
            binding.message.text.clear()
        }

    }
}