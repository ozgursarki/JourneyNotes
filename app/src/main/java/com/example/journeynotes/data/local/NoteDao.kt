package com.example.journeynotes.data.local

import androidx.room.*
import com.example.journeynotes.domain.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAllNotes() : Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    suspend fun getNoteByID(id:Int) : NoteEntity

    @Query("SELECT * FROM NoteEntity WHERE note_location = :location")
    suspend fun getNoteByLocation(location: String) : NoteEntity
}