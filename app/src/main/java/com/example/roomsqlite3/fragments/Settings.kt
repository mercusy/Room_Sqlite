package com.example.roomsqlite3.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.roomsqlite3.R
import com.example.roomsqlite3.databinding.FragmentSettingsBinding

class Settings : Fragment(R.layout.fragment_settings) {
    lateinit var binding: FragmentSettingsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

    }
}