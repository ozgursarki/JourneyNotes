package com.example.journeynotes.domain.use_case

import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.model.Note
import javax.inject.Inject

class EditNotes @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.uptadeNotes(note)
    }
}