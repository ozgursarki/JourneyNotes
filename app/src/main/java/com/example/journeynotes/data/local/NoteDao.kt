package com.example.journeynotes.data.local

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    suspend fun getAllPlaces() : List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NoteEntity)

    @Delete
    suspend fun deletePlace(note: NoteEntity)
}