package com.example.journeynotes.data

import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.data.local.NoteDao
import com.example.journeynotes.data.local.NoteEntity
import com.example.journeynotes.data.mapper.toNote
import com.example.journeynotes.data.mapper.toNoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) {
        noteDao.addNote(note.toNoteEntity())
    }

    fun getNotes(): Flow<List<NoteEntity>> = flow {
        val notes = noteDao.getAllNotes()
        emit(notes)
    }.flowOn(Dispatchers.IO)

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }
}