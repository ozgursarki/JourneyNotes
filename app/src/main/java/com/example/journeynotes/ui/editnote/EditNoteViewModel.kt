package com.example.journeynotes.ui.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.EditNotes
import com.example.journeynotes.domain.use_case.GetNoteByID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val editNoteUseCase : EditNotes,
    private val getNoteByIDUseCase: GetNoteByID
) : ViewModel() {

    fun uptadeNote(title : String,description:String, id:Int, navigateToNotesFragment : () -> Unit) {
        viewModelScope.launch {
            val noteFromDatabase = getNoteByIDUseCase.invoke(id)
            editNoteUseCase.invoke(noteFromDatabase.copy(title = title, description = description))
            navigateToNotesFragment.invoke()
        }
    }

}