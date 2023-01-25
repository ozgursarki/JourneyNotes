package com.example.journeynotes.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.R
import com.example.journeynotes.databinding.NoteRowBinding
import com.example.journeynotes.domain.model.Note
import java.time.format.DateTimeFormatter

class NotesViewHolder(
    private val binding: NoteRowBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(note: Note) {
        binding.noteTitle.text = note.title
        binding.noteDescription.text = note.description
        binding.noteDate.text = note.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) : NotesViewHolder {
            val view = NoteRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            return NotesViewHolder(view)
        }
    }


}