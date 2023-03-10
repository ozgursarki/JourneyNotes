package com.example.journeynotes.data

import com.example.journeynotes.data.local.Converters
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.data.local.NoteDao
import com.example.journeynotes.data.local.NoteEntity
import com.example.journeynotes.data.mapper.toNote
import com.example.journeynotes.data.mapper.toNoteEntity
import com.example.journeynotes.domain.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun addNote(note: Note) {
        noteDao.addNote(note.toNoteEntity())
    }

    fun getNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map {
            it.map { noteEntity ->
                noteEntity.toNote()

            }
        }

    }



    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }

    suspend fun uptadeNotes(note: Note) {
        noteDao.updateNote(note.toNoteEntity())
    }

    suspend fun getNoteByID(id:Int) : Note {
        return noteDao.getNoteByID(id).toNote()
    }

    suspend fun getNoteByLocation(location: Location) : Note {
        return noteDao.getNoteByLocation(Converters.fromLocation(location)!!).toNote()
    }
}