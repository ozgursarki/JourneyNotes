package com.example.journeynotes.domain.use_case


import com.example.journeynotes.data.NoteRepository
import javax.inject.Inject

class GetNoteByID @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id:Int) : com.example.journeynotes.domain.model.Note {
        return repository.getNoteByID(id)
    }
 }