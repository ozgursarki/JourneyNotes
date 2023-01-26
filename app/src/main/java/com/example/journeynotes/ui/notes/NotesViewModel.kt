package com.example.journeynotes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.GetAllNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetAllNotes
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()

    val notes: LiveData<List<Note>> = _notes

    fun getNotesFromDatabase() {
        viewModelScope.launch {
            val newNotes = getNotesUseCase.invoke()
            _notes.value = newNotes

        }
    }
}