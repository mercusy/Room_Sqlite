package com.example.roomsqlite3.adapters

import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomsqlite3.R
import com.example.roomsqlite3.roomDataBase.Data
import kotlin.math.absoluteValue

class CardHolder(view: View): ViewHolder(view)

class CardAdapter(private val items: List<Data>,private val del: (data: Data)->Unit,private val update: (data: Data)->Unit) : Adapter<CardHolder>() {
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
        val c1 : TextView = holder.itemView.findViewById(R.id.C1)
        val c2 : TextView = holder.itemView.findViewById(R.id.C2)
        val c3 : TextView = holder.itemView.findViewById(R.id.C3)
        val c4 : TextView = holder.itemView.findViewById(R.id.C4)
        val c5 : TextView = holder.itemView.findViewById(R.id.C5)
        val c6 : TextView = holder.itemView.findViewById(R.id.C6)
        val c7 : TextView = holder.itemView.findViewById(R.id.C7)
        val css = listOf(c1,c2,c3,c4,c5,c6,c7)
        val card = holder.itemView.findViewById<ConstraintLayout>(R.id.card)
        c1.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C1.absoluteValue))
            card.setBackgroundResource(R.color.C1)
            changeVisibility(c1,css)
        }
        c2.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C2.absoluteValue))
            card.setBackgroundResource(R.color.C2)
            changeVisibility(c2,css)
        }
        c3.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C3.absoluteValue))
            card.setBackgroundResource(R.color.C3)
            changeVisibility(c3,css)
        }
        c4.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C4.absoluteValue))
            card.setBackgroundResource(R.color.C4)
            changeVisibility(c4,css)
        }
        c5.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C5.absoluteValue))
            card.setBackgroundResource(R.color.C5)
            changeVisibility(c5,css)
        }
        c6.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C6.absoluteValue))
            card.setBackgroundResource(R.color.C6)
            changeVisibility(c6,css)
        }
        c7.setOnClickListener {
            update(Data(itemD.id,itemD.title,itemD.message,R.color.C7.absoluteValue))
            card.setBackgroundResource(R.color.C7)
            changeVisibility(c7,css)
        }
        when(itemD.color){
            R.color.C1->{c1.visibility = View.GONE}
            R.color.C2->{c2.visibility = View.GONE}
            R.color.C3->{c3.visibility = View.GONE}
            R.color.C4->{c4.visibility = View.GONE}
            R.color.C5->{c5.visibility = View.GONE}
            R.color.C6->{c6.visibility = View.GONE}
            R.color.C7->{c7.visibility = View.GONE}
        }

        nameS.text = itemD.title
        surNameS.text = itemD.message
        card.setBackgroundResource(itemD.color)

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
    private fun changeVisibility(c: TextView, css: List<TextView>){
        update(css)
        c.visibility = View.GONE
    }
    private fun update(css : List<TextView>){
        for (i in css){
            i.visibility = View.VISIBLE
        }
    }

}