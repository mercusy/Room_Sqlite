package com.example.roomsqlite3.adapters

import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomsqlite3.R
import com.example.roomsqlite3.roomDataBase.Data

class CardHolder(view: View): ViewHolder(view)

sealed interface DataState{
    object Silence: DataState
    class Delete(data: Data): DataState
}

class CardAdapter(private val items: List<Data>,private val del: (data: Data)->Unit) : Adapter<CardHolder>() {
    val liveData = MutableLiveData<DataState>(DataState.Silence)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return CardHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val itemD = items[position]
        val nameS: TextView = holder.itemView.findViewById(R.id.Rtitle)
        val surNameS: TextView = holder.itemView.findViewById(R.id.Rmessage)
        val delete: TextView = holder.itemView.findViewById(R.id.more)
        nameS.text = itemD.title
        surNameS.text = itemD.message
        holder.itemView.findViewById<ConstraintLayout>(R.id.card).setBackgroundResource(itemD.color)

        delete.setOnClickListener{
            val popup = PopupMenu(holder.itemView.context,delete)
            popup.inflate(R.menu.tools)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete -> {del(itemD)
                    return@setOnMenuItemClickListener true}
                    R.id.share -> {
                        return@setOnMenuItemClickListener true
                    }

                    else -> {return@setOnMenuItemClickListener false}

                }
            }
            popup.show()
        }

    }

}