package com.example.roomsqlite3.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsqlite3.adapters.CardAdapter
import com.example.roomsqlite3.R
import com.example.roomsqlite3.databinding.FragmentMainBinding
import com.example.roomsqlite3.roomDataBase.Data
import com.example.roomsqlite3.roomDataBase.LiveData
import com.example.roomsqlite3.roomDataBase.RoomDBViewModel

open class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val viewModel : RoomDBViewModel by viewModels()
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.addFrag.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
            childFragmentManager.popBackStack()
        }
        setHasOptionsMenu(true)
        viewModel.fetchUsers()
        binding.toolbar.inflateMenu(R.menu.options_menu)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.liveData.observe(viewLifecycleOwner){
            when(it){
                is LiveData.AllData ->{
                    it.dataFromDb
                    if (it.dataFromDb.isEmpty()){
                        binding.addFrag.visibility = View.VISIBLE
                    }else{
                        setRecyclerViewScrollListener()
                        binding.addFrag.visibility = View.GONE
                    }
                    binding.recycler.adapter = CardAdapter(it.dataFromDb,::delete,::update,::copy)
                }
                is LiveData.Nothing ->{}
                else -> {}
            }
        }
    }

    private fun delete(data: Data){
       viewModel.delete(data)
    }
    private fun update(data: Data){
        viewModel.update(data)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings ->{
                findNavController().navigate(R.id.action_mainFragment_to_settings2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.addFrag.visibility = View.VISIBLE
                Handler().postDelayed({
                    binding.addFrag.visibility = View.GONE
                },3000)
            }
        }
        binding.recycler.addOnScrollListener(scrollListener)
    }
    private fun copy(data: Data){
        val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("",data.message)
        clipboard.setPrimaryClip(clip)
    }
}