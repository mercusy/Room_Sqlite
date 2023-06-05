package com.example.roomsqlite3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomsqlite3.roomDataBase.Data

class CardHolder(view: View): ViewHolder(view)

class CardAdapter(private val items: List<Data>,private val del: (data: Data)->Unit) : Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return CardHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val item = items[position]
        val nameS: TextView = holder.itemView.findViewById(R.id.name)
        val surNameS: TextView = holder.itemView.findViewById(R.id.surname)
        val delete: TextView = holder.itemView.findViewById(R.id.del)
        nameS.text = item.name
        surNameS.text = item.lastname
        delete.setOnClickListener {
            del(item)
        }
    }
}