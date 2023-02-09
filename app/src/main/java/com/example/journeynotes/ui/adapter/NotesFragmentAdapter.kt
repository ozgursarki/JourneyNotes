package com.example.journeynotes.ui.adapter

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.journeynotes.R
import com.example.journeynotes.ui.adapter.viewholder.NotesViewHolder
import com.example.journeynotes.domain.model.Note

class NotesFragmentAdapter(
    private val noteList: ArrayList<Note> = arrayListOf(),
    private val deleteNoteCallBack : (Note) -> Unit,
    private val callBackNoteClick : (Note) -> Unit
): RecyclerView.Adapter<NotesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.create(parent)
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
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.custom_anim2))
        holder.bind(noteList[position],deleteNoteCallBack = {
            deleteNoteCallBack.invoke(it)
        },callBackNoteClick = {
            callBackNoteClick.invoke(it)
        })
    }

}