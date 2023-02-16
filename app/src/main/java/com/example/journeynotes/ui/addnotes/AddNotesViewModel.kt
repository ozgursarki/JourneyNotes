package com.example.journeynotes.ui.addnotes

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeynotes.R
import com.example.journeynotes.data.NoteRepository
import com.example.journeynotes.domain.model.Note
import com.example.journeynotes.domain.use_case.InsertNote
import com.example.journeynotes.enum.ColorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddNotesViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNote
) : ViewModel() {

    private val _noteColor: MutableLiveData<Int> = MutableLiveData()
    val noteColor: LiveData<Int> = _noteColor

    init {
        colorChanged(Random.nextInt(4))
    }
    fun insertDatatoDatabase(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteUseCase.invoke(note)
        }
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