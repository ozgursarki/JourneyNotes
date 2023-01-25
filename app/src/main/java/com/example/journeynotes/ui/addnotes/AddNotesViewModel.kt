package com.example.journeynotes.ui.addnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.InsertNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNotesViewModel @Inject constructor(
    private val insertNoteUseCase : InsertNote
    )
    : ViewModel() {

    fun insertDatatoDatabase(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.invoke(note)
        }
    }

}