package com.example.journeynotes.domain.use_case

import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.model.Location
import com.example.journeynotes.domain.model.Note
import javax.inject.Inject

class GetNoteByLocation @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(location: Location) : Note {
        return repository.getNoteByLocation(location)

    }
}