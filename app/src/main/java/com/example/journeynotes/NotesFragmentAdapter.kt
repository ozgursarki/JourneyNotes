package com.example.journeynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.model.Place

class NotesFragmentAdapter(private var callback: (Place) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var placeList = emptyList<Place>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    fun setPlaces(place: List<Place>) {
        this.placeList = place
        notifyDataSetChanged()
    }

}