package com.example.journeynotes.domain.use_case

import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.data.local.NoteEntity
import com.example.journeynotes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotes @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke() : Flow<List<Note>> {
        return repository.getNotes()
    }
}