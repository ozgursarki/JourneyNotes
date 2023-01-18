package com.example.journeynotes.data

import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.data.local.NoteDao
import com.example.journeynotes.data.mapper.toNote
import com.example.journeynotes.data.mapper.toNoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) {
        noteDao.addNote(note.toNoteEntity())
    }

    suspend fun getNotes(): List<Note> {
        return noteDao.getAllPlaces().map {
            it.toNote()
        }
    }
}