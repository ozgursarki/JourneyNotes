package com.example.journeynotes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.model.Place

class NotesViewHolder(itemView : View,) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)

    fun getPlace(place: Place) {
        title.text = place.name
        description.text = place.description
    }

}