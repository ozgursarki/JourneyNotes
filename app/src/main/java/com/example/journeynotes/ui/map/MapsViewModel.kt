package com.example.journeynotes.ui.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.GetAllNotes
import com.example.journeynotes.ui.notes.NoteScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val getAllNotesUseCase : GetAllNotes
) : ViewModel() {

    private val _notes : MutableStateFlow<List<Note>> =
        MutableStateFlow(arrayListOf())

    val notes: StateFlow<List<Note>>
        get() = _notes


    fun getAllNotes() {
        viewModelScope.launch {
            val notes = getAllNotesUseCase.invoke()
            notes.collect { noteList ->
                _notes.update {
                    noteList
                }

            }
        }
    }
}