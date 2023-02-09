package com.example.journeynotes.domain.use_case

import com.example.journeynotes.domain.model.Note

class NoteSortUseCase {

    operator fun invoke(noteList : List<Note>, ascending : Boolean) : List<Note> {
        val list = noteList.sortedByDescending {
            it.timeStamp
        }
        return if (ascending) {
            list.reversed()
        }else {
            list
        }
    }
}