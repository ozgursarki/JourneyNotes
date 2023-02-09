package com.example.journeynotes.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.R
import com.example.journeynotes.databinding.NoteRowBinding
import com.example.journeynotes.domain.model.Note
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class NotesViewHolder(
    private val binding: NoteRowBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(note: Note, deleteNoteCallBack : (Note) -> Unit, callBackNoteClick: (Note) -> Unit ) {
        binding.noteTitle.text = note.title
        binding.noteDescription.text = note.description
        val simpleDateFormat = SimpleDateFormat("dd MM yyyy HH:mm", Locale(Locale.getDefault().language))
        val stamp = Timestamp(note.timeStamp)
        val time = simpleDateFormat.format(Date(stamp.time))
        binding.noteDate.text = time
        binding.noteDelete.setOnClickListener{
            deleteNoteCallBack.invoke(note)
        }
        binding.noteEdit.setOnClickListener {
            callBackNoteClick.invoke(note)
        }
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