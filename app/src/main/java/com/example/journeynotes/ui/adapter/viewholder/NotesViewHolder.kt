package com.example.journeynotes.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.R
import com.example.journeynotes.domain.model.Note

class NotesViewHolder(itemView : View,) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)

    fun bind(note: Note) {
        title.text = note.title
        description.text = note.description
    }

}