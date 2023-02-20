package com.example.journeynotes.ui.adapter.viewholder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.R
import com.example.journeynotes.databinding.NoteRowBinding
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.ui.extension.convertToDateFormat
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class NotesViewHolder(
    private val binding: NoteRowBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(note: Note, deleteNoteCallBack : (Note) -> Unit, callBackNoteClick: (Note) -> Unit ) {
        binding.apply {
            Log.e("Deneme", "${note.color}")

            note.color?.let {
                root.setBackgroundResource(it)
                noteDelete.setBackgroundResource(it)
                noteEdit.setBackgroundResource(it)
            }
            noteTitle.text = note.title
            noteDescription.text = note.description

        }

        val time = note.timeStamp.convertToDateFormat()
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