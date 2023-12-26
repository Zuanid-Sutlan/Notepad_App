package com.example.notepadapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private var context: Context, var notes: ArrayList<Notes>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.descripton.text = notes[position].description

        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailNoteActivity::class.java)
            intent.putExtra("title", notes[position].title)
            intent.putExtra("description", notes[position].description)
            context.startActivity(intent)
        }

        holder.btnDel.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("DelTitle", notes[position].title)
            intent.putExtra("DelDescription", notes[position].description)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.tvTitle)
        var descripton: TextView = itemView.findViewById(R.id.tvDescription)
        var btnDel: ImageView = itemView.findViewById(R.id.btnDel)
    }
}