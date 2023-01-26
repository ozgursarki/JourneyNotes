package com.example.journeynotes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.data.mapper.toNote
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.DeleteNote
import com.example.journeynotes.domain.use_case.GetAllNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetAllNotes,
    private val deleteNoteUseCase: DeleteNote
) : ViewModel() {

    private val _notes : MutableStateFlow<NoteScreenUiState> =
        MutableStateFlow(NoteScreenUiState(emptyList()))

    val notes: StateFlow<NoteScreenUiState>
        get() = _notes

    var getNotesJob: Job? = null

    fun getNotesFromDatabase() {
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            getNotesUseCase.invoke().collect {
            _notes.update { noteScreenUiState ->
                noteScreenUiState.copy( it.map {
                    it.toNote()
                })

            }
            }

        }
    }

    fun deleteNoteFromDatabase(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(note)
        }
    }
}