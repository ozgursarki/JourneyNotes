package com.example.journeynotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.ui.adapter.viewholder.NotesViewHolder
import com.example.journeynotes.R
import com.example.journeynotes.domain.model.Note

class NotesFragmentAdapter(private val noteList: ArrayList<Note> = arrayListOf()): RecyclerView.Adapter<NotesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }



    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setNotesList(notes: List<Note>) {
        noteList.clear()
        noteList.addAll(notes)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

}