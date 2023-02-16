package com.example.journeynotes.ui.editnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.R
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.EditNotes
import com.example.journeynotes.domain.use_case.GetNoteByID
import com.example.journeynotes.enum.ColorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val editNoteUseCase : EditNotes,
    private val getNoteByIDUseCase: GetNoteByID
) : ViewModel() {

    private val _noteColor: MutableLiveData<Int> = MutableLiveData()
    val noteColor: LiveData<Int> = _noteColor

    fun uptadeNote(title : String,description:String, id:Int, navigateToNotesFragment : () -> Unit) {
        viewModelScope.launch {
            val noteFromDatabase = getNoteByIDUseCase.invoke(id)
            editNoteUseCase.invoke(noteFromDatabase.copy(
                title = title,
                description = description,
                color = _noteColor.value,
                timeStamp = System.currentTimeMillis()
            ))
            navigateToNotesFragment.invoke()
        }
    }

    fun initializeColor(color: Int) {
        _noteColor.value = color
    }

    fun colorChanged(colorPosition: Int) {
        when (colorPosition) {
            ColorType.RED_ORANGE.position -> {
                _noteColor.value = R.color.RedOrange
            }
            ColorType.RED_PINK.position -> {
                _noteColor.value = R.color.RedPink
            }
            ColorType.BABY_BLUE.position -> {
                _noteColor.value = R.color.BabyBlue
            }
            ColorType.VIOLET.position -> {
                _noteColor.value = R.color.Violet
            }
            ColorType.LIGHT_GREEN.position -> {
                _noteColor.value = R.color.LightGreen
            }
        }
    }

}